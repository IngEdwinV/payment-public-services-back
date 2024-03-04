package edu.escuelaing.arem.ASE.app.service;

import edu.escuelaing.arem.ASE.app.MockPagos;
import edu.escuelaing.arem.ASE.app.configuration.InternalConstantValue.EstadoTrx;
import edu.escuelaing.arem.ASE.app.excepcion.Exception;
import edu.escuelaing.arem.ASE.app.model.RespuestaPago;
import edu.escuelaing.arem.ASE.app.model.TxrModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransaccionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final FacturaService facturaService;

    private final MockPagos mockPagos;

    private final UserService userService;

    public TransaccionService(FacturaService facturaService, MockPagos mockPagos, UserService userService) {
        this.facturaService = facturaService;
        this.mockPagos = mockPagos;
        this.userService = userService;
    }

    public ResponseEntity<RespuestaPago> payment(TxrModel txrModel) {

        if (facturaService.getFactura(txrModel.getIdFactura()) == null)
            throw new Exception.FacturaNotFoundException("No se encontró la factura con ID: " + txrModel.getIdFactura());

        UUID uuid = UUID.randomUUID();
        LocalDate date = LocalDate.now();

        String sql = "INSERT INTO transaccion (id, id_factura, fecha_trx, estado) " +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setInt(2, txrModel.getIdFactura());
            preparedStatement.setDate(3, Date.valueOf(date));
            preparedStatement.setString(4, mockPagos.mockRealizarPago().toString());
        });

        actualizarIdTxrFactura(txrModel.getIdFactura(), uuid.toString());

        RespuestaPago respuestaPago = new RespuestaPago(uuid.toString());
        return new ResponseEntity<>(respuestaPago, HttpStatus.OK);
    }

    public List<Object> getPayments(int user) {

        if (userService.getUsuario(user) == null)
            throw new Exception.UsuarioNotFoundException("No se encontró el usuario con ID: " + user);

        String sql = "SELECT t.* " +
                "FROM transaccion t " +
                "INNER JOIN factura f ON t.id_factura = f.id " +
                "INNER JOIN usuario u ON f.id_usuario = u.id " +
                "WHERE u.id = ?";

        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                TxrModel txr = new TxrModel();
                txr.setId(rs.getString("id"));
                txr.setIdFactura(rs.getInt("id_factura"));
                txr.setFechaTrx(rs.getDate("fecha_trx").toLocalDate());
                txr.setEstado(EstadoTrx.valueOf(rs.getString("estado")));
                return txr;
            }, user);
        } catch (java.lang.Exception e) {
            System.err.println("Error al obtener todas las transacciones por usuario: " + e.getMessage());
            return null;
        }
    }

    public List<Object> getPaymentsStatusByUser(String status, int user) {
        String sql = "SELECT t.* " +
                "FROM transaccion t " +
                "INNER JOIN factura f ON t.id_factura = f.id " +
                "INNER JOIN usuario u ON f.id_usuario = u.id " +
                "WHERE u.id = ? AND t.estado = ?";

        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                TxrModel txr = new TxrModel();
                txr.setId(rs.getString("id"));
                txr.setIdFactura(rs.getInt("id_factura"));
                txr.setFechaTrx(rs.getDate("fecha_trx").toLocalDate());
                txr.setEstado(EstadoTrx.valueOf(rs.getString("estado")));
                return txr;
            }, user, status);
        } catch (java.lang.Exception e) {
            System.err.println("Error al obtener todas las transacciones por estado y usuario: " + e.getMessage());
            return null;
        }
    }

    public List<Object> getPaymentsByFactura(int idFactura) {
        String sql = "SELECT * FROM transaccion WHERE id_factura = ?";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> {
                TxrModel txr = new TxrModel();
                txr.setId(rs.getString("id"));
                txr.setIdFactura(rs.getInt("id_factura"));
                txr.setFechaTrx(rs.getDate("fecha_trx").toLocalDate());
                txr.setEstado(EstadoTrx.valueOf(rs.getString("estado")));
                return txr;
            }, idFactura);
        } catch (java.lang.Exception e) {
            System.err.println("Error al obtener todas las transacciones por factura: " + e.getMessage());
            return null;
        }
    }

    private void actualizarIdTxrFactura(int idFactura, String idTxr) {
        String sql = "UPDATE factura SET id_txr = ? WHERE id = ?";

        jdbcTemplate.update(sql, idTxr, idFactura);
    }

}
