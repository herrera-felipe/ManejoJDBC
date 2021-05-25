package datos;

import java.util.List;
import domain.UsuarioDTO;

public interface UsuarioDao {

	public List<UsuarioDTO> select();
	
	public int insert(UsuarioDTO usuario);
	
	public int update(UsuarioDTO usuario);
	
	public int delete(UsuarioDTO usuario);
}
