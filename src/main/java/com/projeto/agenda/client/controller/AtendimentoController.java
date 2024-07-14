package ziparProKelvin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AtendimentoController implements InterfaceCRUD {

	@Autowired
	private AtendimentoService atendimentoService;

	@Override
	public void salvar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscarTodos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	
	
}
