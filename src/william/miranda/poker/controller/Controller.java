package william.miranda.poker.controller;

/*
 * Classe utilizada para atualizar a view de acordo com as mudancas feitas no modelo
 */
public class Controller
{
	private static Controller controller = null;
	
	private Controller()
	{
		
	}
	
	public static Controller getController()
	{
		if (controller == null)
		{
			controller = new Controller();
		}
		
		return controller;
	}
}
