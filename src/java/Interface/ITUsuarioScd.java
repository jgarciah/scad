/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Pojo.UsuarioScd;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author lcevallosc
 */
public interface ITUsuarioScd {
    public UsuarioScd getByIdUsuario(Session session, Integer  idUsuario) throws Exception;
    public List<UsuarioScd> getAll(Session session) throws Exception;
    public UsuarioScd getByUsuario(Session session,String usuario) throws Exception;
}
