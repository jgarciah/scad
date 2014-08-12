/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import Interface.ITUsuarioScd;
import Pojo.UsuarioScd;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author lcevallosc
 */
public class DaoTUsuarioScd implements ITUsuarioScd {

    @Override
    public UsuarioScd getByIdUsuario(Session session, Integer idUsuario) throws Exception {
        return (UsuarioScd) session.load(UsuarioScd.class,idUsuario);
    }

    @Override
    public List<UsuarioScd> getAll(Session session) throws Exception {
        return session.createCriteria(UsuarioScd.class).list(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioScd getByUsuario(Session session, String usuario) throws Exception {
        String hql="from UsuarioScd where Usuario=:usuarioParam";
        Query query=session.createQuery(hql);
        query.setParameter("usuarioParam", usuario);
        
        return (UsuarioScd)query.uniqueResult();
        
    }
    
}
