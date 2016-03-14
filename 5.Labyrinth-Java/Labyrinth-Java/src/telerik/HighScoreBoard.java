package telerik;

import java.util.LinkedList;


public class HighScoreBoard {
	LinkedList list;
	public final int boardSize;

	public HighScoreBoard(){
		list = new LinkedList();
		boardSize = 5;
	}

	/**
	 * Adding player to high score board, the right conditions are met
	 * @param player player to be added
	 * @return true / false depending on player was added or not.
     */
	public boolean addPlayerToChart(Player player){
		if(list.size()==0){
			list.addFirst(player);
			return true;
		}
		Player pl = (Player) list.get(list.size()-1);

		if((list.size()>0)&&(list.size()<boardSize)){
			// Only one player in high score board
			if(player.movesCount>pl.movesCount){
				list.addLast(player);
				return true;
			}
			if(searchIndex(player)) {
				return true;
			}
		}

		// The board is full
		if(list.size()==boardSize) {
			if(removePlayer(pl, player)) {
				return true;
			}
		}
		return false;
	}

	private boolean removePlayer(Player pl, Player player) {
		if(player.movesCount<pl.movesCount){
			list.remove(list.size() - 1);
			if(searchIndex(player)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method for checking if player should be added to high score board
	 * @param player player to be added
     * @return
     */
	private boolean searchIndex(Player player) {
		int index = 0;
		while (index < list.size()) {
			Player pl = (Player) list.get(index);

			if (player.movesCount <= pl.movesCount) {
				list.add(index, player);
				return true;
			}
			index++;
		}
		return false;
	}

	void printBoard(LinkedList list){
		System.out.println("Score :");
		for(int i=0;i<list.size();i++){
			Player p = (Player) list.get(i);
			System.out.print((i+1)+". Name - "+p.name+" ");
			System.out.print("Escaped in "+p.movesCount+" moves");
			System.out.println();
		}
	}
}
