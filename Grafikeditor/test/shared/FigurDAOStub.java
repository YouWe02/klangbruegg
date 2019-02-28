package shared;

import util.FigurDAO;

public class FigurDAOStub implements FigurDAO {
	
	private String[] testFiguren = {
			"Rechteck,100,100,50,100",
			"Kreis,200,200,25",
			"Linie,300,300,400,400"
	};
	
	private int currentFigur = 0;
	
	@Override
	public String[] readNextFigurData(){
		if(this.currentFigur < this.testFiguren.length) {
			String[] figurData = this.testFiguren[this.currentFigur].split(",");
			currentFigur++;
			return figurData;
		}
		return null;
	}

}
