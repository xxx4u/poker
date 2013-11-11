package william.miranda.poker.view;

public class PlayerSlot
{
	/* coordenadas das cartas */
	private int x;
	private int y;
	
	/* coordenadas do dealer button */
	private int dealerX;
	private int dealerY;
	
	//retorna as coordenadas de acordo com a posicao da mesa
	public static PlayerSlot getSlot(int pos)
	{
		PlayerSlot slot = new PlayerSlot();
		
		switch (pos)
		{
			case 0:
				slot.setX(ViewUtils.getLargura()/2-80);
				slot.setY(0);
				slot.setDealerX(ViewUtils.getLargura()/2-80);
				slot.setDealerY(100);
				break;
				
			case 1:
				slot.setX(0);
				slot.setY(ViewUtils.getAltura()/4-50);
				slot.setDealerX(15);
				slot.setDealerY(ViewUtils.getAltura()/4-30);
				break;
				
			case 2:
				slot.setX(0);
				slot.setY(ViewUtils.getAltura()/2-50);
				slot.setDealerX(15);
				slot.setDealerY(ViewUtils.getAltura()/2-30);
				break;
				
			case 3:
				slot.setX(0);
				slot.setY(3*ViewUtils.getAltura()/4-50);
				slot.setDealerX(15);
				slot.setDealerY(3*ViewUtils.getAltura()/4-30);
				break;
				
			case 4:
				slot.setX(ViewUtils.getLargura()/2-80);
				slot.setY(ViewUtils.getAltura()-100);
				slot.setDealerX(ViewUtils.getLargura()/2-50);
				slot.setDealerY(ViewUtils.getAltura()-60);
				break;
				
			case 5:
				slot.setX(ViewUtils.getLargura()-160);
				slot.setY(3*ViewUtils.getAltura()/4-50);
				slot.setDealerX(ViewUtils.getLargura()-80);
				slot.setDealerY(3*ViewUtils.getAltura()/4-30);
				break;
				
			case 6:
				slot.setX(ViewUtils.getLargura()-160);
				slot.setY(ViewUtils.getAltura()/2-50);
				slot.setDealerX(ViewUtils.getLargura()-80);
				slot.setDealerY(ViewUtils.getAltura()/2-30);
				break;
				
			case 7:
				slot.setX(ViewUtils.getLargura()-160);
				slot.setY(ViewUtils.getAltura()/4-50);
				slot.setDealerX(ViewUtils.getLargura()-80);
				slot.setDealerY(ViewUtils.getAltura()/4-30);
				break;
		}
		
		return slot;
	}
	
	public int getDealerX() {
		return dealerX;
	}

	public void setDealerX(int dealerX) {
		this.dealerX = dealerX;
	}

	public int getDealerY() {
		return dealerY;
	}

	public void setDealerY(int dealerY) {
		this.dealerY = dealerY;
	}

	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getY()
	{
		return this.y;
	}
}
