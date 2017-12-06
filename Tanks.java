import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.net.URL;
import java.io.File;
import java.util.Random;
import java.util.Calendar;
import java.io.PrintWriter;
import java.lang.StringBuilder;


public class Tanks extends JPanel {  			// Class's name


public static void main(String[] args) {			//Main function. The code starts here

	JFrame window = new JFrame("Tanks");
      Tanks content = new Tanks();
      window.setContentPane(content);
      window.pack();
      Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
      window.setLocation( (screensize.width - window.getWidth())/2,
            (screensize.height - window.getHeight())/2 );
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setResizable(false);
      window.setVisible(true);

}





   private JLabel message; 							//JLabel for Tanks's layout

   private JLabel roundMessage;

   private JLabel instructionMessage;

   private JLabel commandMessage;

   private JLabel commandList;

   private JLabel commandListEnemy;

   private JLabel mode;

   private JLabel record;

   private JLabel inputTheCommand;

   private JLabel remainingMove;


   private JButton startGame;						//JButton for Tanks's layout

   private JButton saveGame;

   private JButton loadGame;

   private JButton upMove;

   private JButton downMove;

   private JButton leftMove;

   private JButton rightMove;

   private JButton undoMove;

   private JButton startMove;

   private JButton shootUp;

   private JButton shootDown;

   private JButton shootLeft;

   private JButton shootRight;



   private JTextArea commandSequence;						//JTextArea for Tanks layout

   private JTextArea commandSequenceEnemy;





   public Tanks() {				//[Li Kwan]							//Constructor for Tanks class

      setLayout(null);
      setPreferredSize( new Dimension(1300,650) );

      setBackground(new Color(0,150,0));



      Board board = new Board(); 						//Creating Board object


      add(board);										//Adding items to Board object

      add(message);
	  add(roundMessage);
	  add(commandMessage);
	  add(commandList);
	  add(commandListEnemy);
	  add(mode);
	  add(record);
	  add(remainingMove);

	  add(startGame);
	  add(saveGame);
	  add(loadGame);


	  add(inputTheCommand);
	  add(startMove);

	  add(upMove);
	  add(downMove);
	  add(leftMove);
	  add(rightMove);

	  add(undoMove);

	  add(shootUp);
	  add(shootDown);
	  add(shootLeft);
	  add(shootRight);

      add(commandSequence);
	  add(commandSequenceEnemy);

      board.setBounds(170,140,304,304);

      message.setBounds(500, 10, 200, 30);
	  roundMessage.setBounds(500, 40, 200, 30);
	  mode.setBounds(450, 60, 300, 30);
	  commandMessage.setBounds(900 ,10 ,300 ,30);
	  record.setBounds(2, 20, 300, 30);
	  remainingMove.setBounds(750, 550, 100, 30);

	  startGame.setBounds(750, 100, 120, 30);
	  saveGame.setBounds(950, 100, 120, 30);
      loadGame.setBounds(1150, 100, 120, 30);


	  inputTheCommand.setBounds(750, 250, 120, 20);
	  startMove.setBounds(1110, 500, 120, 50);

	  upMove.setBounds(750, 300, 120, 50);
	  downMove.setBounds(870, 300, 120, 50);
	  leftMove.setBounds(990, 300, 120, 50);
	  rightMove.setBounds(1110, 300, 120, 50);
	  shootUp.setBounds(750, 400, 120, 50);
	  shootDown.setBounds(870, 400, 120, 50);
	  shootLeft.setBounds(990, 400, 120, 50);
	  shootRight.setBounds(1110, 400, 120, 50);


	  undoMove.setBounds(750, 500, 120, 50);

	  commandList.setBounds(2, 100, 150, 30);
	  commandSequence.setBounds(2, 140, 150, 450);
	  commandListEnemy.setBounds(475, 100, 200, 30);
	  commandSequenceEnemy.setBounds(500, 140, 150, 450);

   } // end constructor





   private class Board extends JPanel implements ActionListener{	//[Christopher]		//Creating class Board



      private String difficulty = "Easy";

      int currentPlayer;

      int selectedRow, selectedCol;



	  private int myTankX;

	  private int myTankY;

	  private int enemyTankX;

	  private int enemyTankY;

	  private String myCurrentPosition;

	  private String enemyCurrentPosition;

	  private ArrayList<String> listOfCommand = new ArrayList<String>();

	  private int roundNumber = 1;

	  private int recordNumberEasy;

	  private int recordNumberHard;

	  private int moveLeft = 18;

	  private boolean nextRound = false;


      Board() {							//[SHAKIF FAHIM & SARZIL MAHMOOD]				//Constructor for Board class
         setBackground(Color.BLACK);

         message = new JLabel("",JLabel.CENTER);
         message.setFont(new  Font("Serif", Font.BOLD, 14));
         message.setForeground(Color.green);
		 message.setText("Welcome to Tanks");

		 roundMessage = new JLabel("",JLabel.CENTER);
         roundMessage.setFont(new  Font("Serif", Font.BOLD, 14));
         roundMessage.setForeground(Color.yellow);
		 roundMessage.setText("Round: ");

		 commandMessage = new JLabel("Press Start Game to start the game",JLabel.CENTER);
         commandMessage.setFont(new  Font("Serif", Font.BOLD, 14));
		 commandMessage.setForeground(Color.green);



		 mode = new JLabel("Mode: " + difficulty,JLabel.CENTER);
         mode.setFont(new  Font("Serif", Font.BOLD, 14));
		 mode.setForeground(Color.orange);

		 record = new JLabel("Record: ",JLabel.CENTER);
         record.setFont(new  Font("Serif", Font.BOLD, 14));
		 record.setForeground(Color.white);
		 getRecord();
		 record.setText("Current Record - Easy: " + recordNumberEasy + " Hard: " + recordNumberHard);

		 remainingMove = new JLabel("Move Left: " + moveLeft,JLabel.CENTER);
         remainingMove.setFont(new  Font("Serif", Font.BOLD, 14));
		 remainingMove.setForeground(Color.yellow);

		 startGame = new JButton("Start Game");
		 startGame.addActionListener(this);
		 saveGame = new JButton("Save");
		 saveGame.addActionListener(this);
		 loadGame = new JButton("Load");
		 loadGame.addActionListener(this);


		 inputTheCommand = new JLabel("Select Movement: ");
		 startMove = new JButton("Start Move");
		 startMove.addActionListener(this);

		 upMove = new JButton("Go Up");
		 upMove.addActionListener(this);
		 downMove = new JButton("Go Down");
		 downMove.addActionListener(this);
		 leftMove = new JButton("Go Left");
		 leftMove.addActionListener(this);
		 rightMove = new JButton("Go Right");
		 rightMove.addActionListener(this);

		 shootUp = new JButton("Shoot Up");
		 shootUp.addActionListener(this);
		 shootDown = new JButton("Shoot Down");
		 shootDown.addActionListener(this);
		 shootLeft = new JButton("Shoot Left");
		 shootLeft.addActionListener(this);
		 shootRight = new JButton("Shoot Right");
		 shootRight.addActionListener(this);

		 undoMove = new JButton("Undo");
		 undoMove.addActionListener(this);


		 commandList = new JLabel("User's Commend List:",JLabel.CENTER);
         commandList.setFont(new  Font("Serif", Font.BOLD, 14));
		 commandSequence = new JTextArea(10,20);

		 commandListEnemy = new JLabel("Enemy's Commend List:",JLabel.CENTER);
         commandListEnemy.setFont(new  Font("Serif", Font.BOLD, 14));
		 commandSequenceEnemy = new JTextArea(10,20);

		 upMove.setEnabled(false);
		 downMove.setEnabled(false);
		 leftMove.setEnabled(false);
		 rightMove.setEnabled(false);
		 shootUp.setEnabled(false);
		 shootDown.setEnabled(false);
		 shootLeft.setEnabled(false);
		 shootRight.setEnabled(false);
		 startMove.setEnabled(false);
		 undoMove.setEnabled(false);

        // board = new TanksData();
         //doNewGame();
		 myTankX = 2;

	     myTankY = 2;

		 enemyTankX = 272;

	     enemyTankY = 272;

		 roundNumber = 1;
		 myCurrentPosition = "LOOK DOWN";
		 enemyCurrentPosition = "LOOK UP";




		 repaint();
      }



      public void actionPerformed(ActionEvent evt) {	//[Christopher]					//Codes for buttons clicking
         Object obj = evt.getSource();


		if (obj == startGame){

			startTheGame();
		 }
		else if (obj == saveGame){

			saveTheGame();

		}
		else if (obj == loadGame){
			if(roundNumber >= 2){
					Object[] optionsB = { "Yes", "No" };
					int choiceOverwrite = JOptionPane.showOptionDialog(null,
					"You will remove the current game. Continue?",
					"Loading Game",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, optionsB, optionsB[0]);
					if(choiceOverwrite == JOptionPane.YES_OPTION){

						loadTheGame();

					}
			}
			else{
				loadTheGame();
			}

		}

		else if(obj == startMove){

			startTheRound();

		}

		else if(obj == upMove){
			if(nextRound == true){
				 commandSequence.setText(null);
				 nextRound = false;
			}

			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{

				listOfCommand.add("Go Up");
				commandSequence.append("\n");
				commandSequence.append("Go Up");
				commandMessage.setText("");
				moveLeft = 18 - listOfCommand.size();
				remainingMove.setText("Move Left: " + moveLeft);

			}
		}
		else if(obj == downMove){
			if(nextRound == true){
				 commandSequence.setText(null);
				 nextRound = false;
			}

			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{

				listOfCommand.add("Go Down");
				commandSequence.append("\n");
				commandSequence.append("Go Down");
				commandMessage.setText("");
				moveLeft = 18 - listOfCommand.size();
				remainingMove.setText("Move Left: " + moveLeft);
			}
		}
		else if(obj == leftMove){
			if(nextRound == true){
				 commandSequence.setText(null);
				 nextRound = false;
			}

			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{

				listOfCommand.add("Go Left");
				commandSequence.append("\n");
				commandSequence.append("Go Left");
				commandMessage.setText("");
				moveLeft = 18 - listOfCommand.size();
				remainingMove.setText("Move Left: " + moveLeft);
			}
		}
		else if(obj == rightMove){
			if(nextRound == true){
				 commandSequence.setText(null);
				 nextRound = false;
			}

			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{

				listOfCommand.add("Go Right");
				commandSequence.append("\n");
				commandSequence.append("Go Right");
				commandMessage.setText("");
				moveLeft = 18 - listOfCommand.size();
				remainingMove.setText("Move Left: " + moveLeft);
			}
		}
		else if(obj == shootUp){
			if(nextRound == true){
				 commandSequence.setText(null);
				 nextRound = false;
			}

			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{

				listOfCommand.add("Shoot Up");
				commandSequence.append("\n");
				commandSequence.append("Shoot Up");
				commandMessage.setText("");
				moveLeft = 18 - listOfCommand.size();
				remainingMove.setText("Move Left: " + moveLeft);
			}
		}
		else if(obj == shootDown){
			if(nextRound == true){
				 commandSequence.setText(null);
				 nextRound = false;
			}

			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{

				listOfCommand.add("Shoot Down");
				commandSequence.append("\n");
				commandSequence.append("Shoot Down");
				commandMessage.setText("");
				moveLeft = 18 - listOfCommand.size();
				remainingMove.setText("Move Left: " + moveLeft);
			}
		}
		else if(obj == shootLeft){
			if(nextRound == true){
				 commandSequence.setText(null);
				 nextRound = false;
			}

			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				listOfCommand.add("Shoot Left");
				commandSequence.append("\n");
				commandSequence.append("Shoot Left");
				commandMessage.setText("");
				moveLeft = 18 - listOfCommand.size();
				remainingMove.setText("Move Left: " + moveLeft);
			}
		}
		else if(obj == shootRight){
			if(nextRound == true){
				 commandSequence.setText(null);
				 nextRound = false;
			}

			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{

				listOfCommand.add("Shoot Right");
				commandSequence.append("\n");
				commandSequence.append("Shoot Right");
				commandMessage.setText("");
				moveLeft = 18 - listOfCommand.size();
				remainingMove.setText("Move Left: " + moveLeft);
			}
		}

		else if(obj == undoMove){
			if(nextRound == true){
				 commandSequence.setText(null);
				 nextRound = false;
			}

			if(listOfCommand.size() >= 1){
				listOfCommand.remove(listOfCommand.size() - 1);
				commandSequence.setText(null);
				if(listOfCommand.size() >= 1){
					int a = listOfCommand.size() - 1;
					for(int i = 0; i <= a; i++){
						commandSequence.append("\n");
						commandSequence.append(listOfCommand.get(i));
					}
				}
			}
			commandMessage.setText("");
			if(moveLeft != 18){
				moveLeft++;
				remainingMove.setText("Move Left: " + moveLeft);
			}
		}

      }

	public void startTheGame(){		//[Christopher]									//Initiates the start of the game
		Object[] options = { "Easy", "Hard", "Cancel" };
		int choice = JOptionPane.showOptionDialog(null,
		"Choose the game's difficulty",
		"New Game",
		JOptionPane.YES_NO_CANCEL_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null, options,
		options[0]);
		int choiceOverwrite;

		if(choice == JOptionPane.YES_OPTION)
		{
			if(roundNumber > 1){
				Object[] optionsB = { "Yes", "No" };
				choiceOverwrite = JOptionPane.showOptionDialog(null,
				"You will remove the current game. Continue?",
				"Remove Current Game",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, optionsB, optionsB[0]);
				if(choiceOverwrite == JOptionPane.YES_OPTION){
					restart("Easy");


				}
		}
		else{
			restart("Easy");
		}
		}
		else if(choice == JOptionPane.NO_OPTION){
			if(roundNumber > 1){
				Object[] optionsB = { "Yes", "No" };
				choiceOverwrite = JOptionPane.showOptionDialog(null,
				"You will remove the current game. Continue?",
				"Remove Current Game",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, optionsB, optionsB[0]);
				if(choiceOverwrite == JOptionPane.YES_OPTION){
					restart("Hard");


				}
		}
		else{
			restart("Hard");
		}
		}
	}
	public void restart(String writtenDifficulty){		//[Christopher]			//Start the game based on the requirement
		roundNumber = 1;
		myTankX = 2;
		myTankY = 2;

		enemyTankX = 272;
		enemyTankY = 272;

		myCurrentPosition = "LOOK DOWN";
		enemyCurrentPosition = "LOOK UP";

		difficulty = writtenDifficulty;
		mode.setText("Mode: " + difficulty);




		repaint();

		upMove.setEnabled(true);
		downMove.setEnabled(true);
		leftMove.setEnabled(true);
		rightMove.setEnabled(true);
		shootUp.setEnabled(true);
		shootDown.setEnabled(true);
		shootLeft.setEnabled(true);
		shootRight.setEnabled(true);
		startMove.setEnabled(true);
		undoMove.setEnabled(true);

		commandSequence.setText(null);
		commandSequenceEnemy.setText(null);
		commandMessage.setText("");
	}
	public void saveTheGame(){			//[Christopher]							//Saving the current game
		try{
			if(roundNumber > 1){
				int loaded;
				URL savePath = Tanks.class.getResource("command.txt");
				File saveFile = new File(savePath.getFile());
				BufferedReader saveReader = new BufferedReader(new FileReader(saveFile));
				if((loaded = saveReader.read()) != -1){
					Object[] optionsB = { "Yes", "No" };
					int choiceOverwrite = JOptionPane.showOptionDialog(null,
					"You will overwrite the saved game. Continue?",
					"Overwriting Game",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, optionsB, optionsB[0]);
					if(choiceOverwrite == JOptionPane.YES_OPTION){
						URL path = Tanks.class.getResource("command.txt");
						File f = new File(path.getFile());
						BufferedWriter bWriter = new BufferedWriter(new FileWriter(f));
						bWriter.write(Integer.toString(roundNumber) + "A");
						bWriter.write(Integer.toString(myTankX) + "A");
						bWriter.write(Integer.toString(myTankY) + "A");
						bWriter.write(Integer.toString(enemyTankX) + "A");
						bWriter.write(Integer.toString(enemyTankY) + "A");
						bWriter.write(difficulty);

						bWriter.close();


					}
				}
				else{
						URL path = Tanks.class.getResource("command.txt");
						File f = new File(path.getFile());
						BufferedWriter bWriter = new BufferedWriter(new FileWriter(f));
						bWriter.write(Integer.toString(roundNumber) + "A");
						bWriter.write(Integer.toString(myTankX) + "A");
						bWriter.write(Integer.toString(myTankY) + "A");
						bWriter.write(Integer.toString(enemyTankX) + "A");
						bWriter.write(Integer.toString(enemyTankY) + "A");
						bWriter.write(difficulty);

						bWriter.close();
				}
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void loadTheGame(){			//[Christopher]						//Loading the saved game
		try{
			ArrayList<Character> loadRoundNumber = new ArrayList<Character>();
			ArrayList<Character> loadMyTankX = new ArrayList<Character>();
			ArrayList<Character> loadMyTankY = new ArrayList<Character>();
			ArrayList<Character> loadEnemyTankX = new ArrayList<Character>();
			ArrayList<Character> loadEnemyTankY = new ArrayList<Character>();
			ArrayList<Character> loadDifficulty = new ArrayList<Character>();



			int loaded;
			URL path = Tanks.class.getResource("command.txt");
			File f = new File(path.getFile());
			BufferedReader loadReader = new BufferedReader(new FileReader(f));
			while((loaded = loadReader.read()) != -1){

				if((char)loaded != 'A'){
					loadRoundNumber.add((char)loaded);
				}

				else{
							break;
					}
			}

			while((loaded = loadReader.read()) != -1){

				if((char)loaded != 'A'){
					loadMyTankX.add((char)loaded);
				}

				else{
						break;
				}
			}

			while((loaded = loadReader.read()) != -1){

				if((char)loaded != 'A'){
					loadMyTankY.add((char)loaded);
				}

				else{
						break;
				}
			}

			while((loaded = loadReader.read()) != -1){

				if((char)loaded != 'A'){
					loadEnemyTankX.add((char)loaded);
				}

				else{
						break;
				}
			}

			while((loaded = loadReader.read()) != -1){

				if((char)loaded != 'A'){
					loadEnemyTankY.add((char)loaded);
				}

				else{
						break;
				}
			}

			while((loaded = loadReader.read()) != -1){

				if((char)loaded == 'E'){
					difficulty = "Easy";
				}
				else if((char)loaded == 'H'){
					difficulty = "Hard";
				}

			}

			StringBuilder roundBuilder = new StringBuilder(loadRoundNumber.size());
			for(Character ch: loadRoundNumber)
			{
				roundBuilder.append(ch);
			}

			StringBuilder myXBuilder = new StringBuilder(loadMyTankX.size());
			for(Character ch: loadMyTankX)
			{
				myXBuilder.append(ch);
			}

			StringBuilder myYBuilder = new StringBuilder(loadMyTankY.size());
			for(Character ch: loadMyTankY)
			{
				myYBuilder.append(ch);
			}
			StringBuilder enemyXBuilder = new StringBuilder(loadEnemyTankX.size());
			for(Character ch: loadEnemyTankX)
			{
				enemyXBuilder.append(ch);
			}
			StringBuilder enemyYBuilder = new StringBuilder(loadEnemyTankY.size());
			for(Character ch: loadEnemyTankY)
			{
				enemyYBuilder.append(ch);
			}
			StringBuilder difficultyBuilder = new StringBuilder(loadDifficulty.size());
			for(Character ch: loadDifficulty)
			{
				difficultyBuilder.append(ch);
			}

			roundNumber = Integer.parseInt(roundBuilder.toString());
			myTankX = Integer.parseInt(myXBuilder.toString());
			myTankY = Integer.parseInt(myYBuilder.toString());
			enemyTankX = Integer.parseInt(enemyXBuilder.toString());
			enemyTankY = Integer.parseInt(enemyYBuilder.toString());

			mode.setText("Mode: " + difficulty);
			repaint();
			upMove.setEnabled(true);
			downMove.setEnabled(true);
			leftMove.setEnabled(true);
			rightMove.setEnabled(true);
			shootUp.setEnabled(true);
			shootDown.setEnabled(true);
			shootLeft.setEnabled(true);
			shootRight.setEnabled(true);
			startMove.setEnabled(true);
			undoMove.setEnabled(true);
			startGame.setEnabled(true);
			saveGame.setEnabled(true);
			loadGame.setEnabled(true);
			moveLeft = 18;
			remainingMove.setText("Move Left: " + moveLeft);
			listOfCommand.clear();
			commandSequence.setText(null);
			commandSequenceEnemy.setText(null);
			commandMessage.setText("");

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public void getRecord(){	//[Christopher]							//Collecting the newest record from the text file
		try{
			int loaded;

			ArrayList<Character> recordLengthEasy = new ArrayList<Character>();

			URL recordPathEasy = Tanks.class.getResource("recordEasy.txt");

			File recordFileEasy = new File(recordPathEasy.getFile());

			BufferedReader recordReaderEasy = new BufferedReader(new FileReader(recordFileEasy));

			while((loaded = recordReaderEasy.read()) != -1){

				recordLengthEasy.add((char)loaded);



			}

			StringBuilder recordBuilderEasy = new StringBuilder(recordLengthEasy.size());
			for(Character ch: recordLengthEasy)
			{
				recordBuilderEasy.append(ch);
			}

			recordNumberEasy = Integer.parseInt(recordBuilderEasy.toString());


			ArrayList<Character> recordLengthHard = new ArrayList<Character>();

			URL recordPathHard = Tanks.class.getResource("recordHard.txt");

			File recordFileHard = new File(recordPathHard.getFile());

			BufferedReader recordReaderHard = new BufferedReader(new FileReader(recordFileHard));

			while((loaded = recordReaderHard.read()) != -1){

				recordLengthHard.add((char)loaded);

			}


			StringBuilder recordBuilderHard = new StringBuilder(recordLengthHard.size());
			for(Character ch: recordLengthHard)
			{
				recordBuilderHard.append(ch);
			}

			recordNumberHard = Integer.parseInt(recordBuilderHard.toString());

			}
		catch(Exception e){
				e.printStackTrace();
			}
	}











	public void startTheRound(){		//[Scruti]							//Initiates the start of the round

		boolean correctPosition;
		correctPosition = checkMyPosition();
		if(listOfCommand.size() < 18){
			 JOptionPane.showMessageDialog(null, "You must input 18 moves!!", "Insufficient Moves", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
				if(correctPosition){

					makeMove();

				}

			 }


	public boolean checkMyPosition(){	//[Li Kwan]							//Checking the expected position for every move
		  boolean inRange = true;

		  int expectedX = myTankX;
		  int expectedY = myTankY;
		  for(int i = 0; i <listOfCommand.size(); i++){
			  if(listOfCommand.get(i) == "Go Up"){
				  expectedY = expectedY - 30;
				  if(expectedY < 2){
					 JOptionPane.showMessageDialog(null, "Out of board range!!", "Out of Range", JOptionPane.INFORMATION_MESSAGE);
					  return false;
				  }

			  }
			  else if(listOfCommand.get(i) == "Go Down"){
				  expectedY = expectedY + 30;
				  if(expectedY > 272){
					 JOptionPane.showMessageDialog(null, "Out of board range!!", "Out of Range", JOptionPane.INFORMATION_MESSAGE);
					  return false;
				  }

			  }
			  else if(listOfCommand.get(i) == "Go Left"){
				  expectedX = expectedX - 30;
				  if(expectedX < 2){
					 JOptionPane.showMessageDialog(null, "Out of board range!!", "Out of Range", JOptionPane.INFORMATION_MESSAGE);
					  return false;
				  }

			  }

			  else if(listOfCommand.get(i) == "Go Right"){
				  expectedX = expectedX + 30;
				  if(expectedX > 272){
					 JOptionPane.showMessageDialog(null, "Out of board range!!", "Out of Range", JOptionPane.INFORMATION_MESSAGE);
					  return false;
				  }


			  }
			  if(i==1){
				if((expectedX == enemyTankX) && (expectedY == enemyTankY)){
					  JOptionPane.showMessageDialog(null, "First move is clasing the tank!!", "Clasing the Tank", JOptionPane.INFORMATION_MESSAGE);
					  return false;
			}
			  }
		  }
		  return true;
	  }



	public void makeMove(){		//[Christopher]									//Initiates the movement of both tanks
		commandSequenceEnemy.setText(null);
		upMove.setEnabled(false);
		downMove.setEnabled(false);
		leftMove.setEnabled(false);
		rightMove.setEnabled(false);
		shootUp.setEnabled(false);
		shootDown.setEnabled(false);
		shootLeft.setEnabled(false);
		shootRight.setEnabled(false);
		startMove.setEnabled(false);
		undoMove.setEnabled(false);
		startGame.setEnabled(false);
		saveGame.setEnabled(false);
		loadGame.setEnabled(false);

        try{
		Thread t1 = new Thread(new Runnable() {
		public void run() {
			try{
				commandMessage.setText("");
				int expectedX = enemyTankX;
				int expectedY = enemyTankY;
				Random enemyDecision;
				int enemyMove;
				boolean validMove = false;
				boolean endOfGame = false;

		//char[] myMoveSet = inputCommand.getText().toCharArray();
				String winner;

				for(int i = 0; i < listOfCommand.size(); i++){


		if(listOfCommand.get(i) == "Go Up"){
				myTankY = myTankY - 30;
				myCurrentPosition = "GO UP";
		}
		else if(listOfCommand.get(i) == "Go Down"){
				myTankY = myTankY + 30;
				myCurrentPosition = "GO DOWN";
		}
		else if(listOfCommand.get(i) == "Go Left"){
				myTankX = myTankX - 30;
				myCurrentPosition = "GO LEFT";
		}
		else if(listOfCommand.get(i) == "Go Right"){
				myTankX = myTankX + 30;
				myCurrentPosition = "GO RIGHT";
		}
		else if(listOfCommand.get(i) == "Shoot Up"){
				myCurrentPosition = "SHOOT UP";
		}
		else if(listOfCommand.get(i) == "Shoot Down"){
				myCurrentPosition = "SHOOT DOWN";
		}
		else if(listOfCommand.get(i) == "Shoot Left"){
				myCurrentPosition = "SHOOT LEFT";
		}
		else if(listOfCommand.get(i) == "Shoot Right"){
				myCurrentPosition = "SHOOT RIGHT";
		}

		validMove = false;
		if(difficulty == "Easy"){
			while(validMove == false){
				expectedX = enemyTankX;
				expectedY = enemyTankY;
				enemyDecision = new Random();
				enemyMove = enemyDecision.nextInt((4 - 1) + 1) + 1;
				if(enemyMove == 1){
					expectedX = expectedX + 30;
					if((expectedX <= 272)&&(!((expectedX == myTankX)&&(enemyTankY == myTankY)))){
						enemyTankX = enemyTankX + 30;
						enemyCurrentPosition = "GO RIGHT";
						validMove = true;
						commandSequenceEnemy.append("\n");
						commandSequenceEnemy.append("Go Right");
					}
					else{
						validMove = false;
					}

				}
				else if(enemyMove == 2){
					expectedX = expectedX - 30;
					if((expectedX >= 2)&&(!((expectedX == myTankX)&&(enemyTankY == myTankY)))){
						enemyTankX = enemyTankX - 30;
						enemyCurrentPosition = "GO LEFT";
						validMove = true;
						commandSequenceEnemy.append("\n");
						commandSequenceEnemy.append("Go Left");
					}
					else{
						validMove = false;
					}
				}
				else if(enemyMove == 3){
					expectedY = expectedY + 30;
					if((expectedY <= 272)&&(!((expectedY == myTankY)&&(enemyTankX == myTankX)))){
						enemyTankY = enemyTankY + 30;
						enemyCurrentPosition = "GO DOWN";
						validMove = true;
						commandSequenceEnemy.append("\n");
						commandSequenceEnemy.append("Go Down");
					}
					else{
						validMove = false;
					}
				}
				else if(enemyMove == 4){
					expectedY = expectedY - 30;
					if((expectedY >= 2)&&(!((expectedY == myTankY)&&(enemyTankX == myTankX)))){
						enemyTankY = enemyTankY - 30;
						enemyCurrentPosition = "GO UP";
						validMove = true;
						commandSequenceEnemy.append("\n");
						commandSequenceEnemy.append("Go Up");
					}
					else{
						validMove = false;
					}
				}

			}

		}
		else if(difficulty == "Hard"){
			while(validMove == false){
				expectedX = enemyTankX;
				expectedY = enemyTankY;
				enemyDecision = new Random();
				enemyMove = enemyDecision.nextInt((8 - 1) + 1) + 1;
				if(enemyMove == 1){
					expectedX = expectedX + 30;
					if((expectedX <= 272)&&(!((expectedX == myTankX)&&(enemyTankY == myTankY)))){
						enemyTankX = enemyTankX + 30;
						enemyCurrentPosition = "GO RIGHT";
						validMove = true;
						commandSequenceEnemy.append("\n");
						commandSequenceEnemy.append("Go Right");
					}
					else{
						validMove = false;
					}

				}
				else if(enemyMove == 2){
					expectedX = expectedX - 30;
					if((expectedX >= 2)&&(!((expectedX == myTankX)&&(enemyTankY == myTankY)))){
						enemyTankX = enemyTankX - 30;
						enemyCurrentPosition = "GO LEFT";
						validMove = true;
						commandSequenceEnemy.append("\n");
						commandSequenceEnemy.append("Go Left");
					}
					else{
						validMove = false;
					}
				}
				else if(enemyMove == 3){
					expectedY = expectedY + 30;
					if((expectedY <= 272)&&(!((expectedY == myTankY)&&(enemyTankX == myTankX)))){
						enemyTankY = enemyTankY + 30;
						enemyCurrentPosition = "GO DOWN";
						validMove = true;
						commandSequenceEnemy.append("\n");
						commandSequenceEnemy.append("Go Down");
					}
					else{
						validMove = false;
					}
				}
				else if(enemyMove == 4){
					expectedY = expectedY - 30;
					if((expectedY >= 2)&&(!((expectedY == myTankY)&&(enemyTankX == myTankX)))){
						enemyTankY = enemyTankY - 30;
						enemyCurrentPosition = "GO UP";
						validMove = true;
						commandSequenceEnemy.append("\n");
						commandSequenceEnemy.append("Go Up");
					}
					else{
						validMove = false;
					}
				}
				else if(enemyMove == 5){
				if(((myTankX < (enemyTankX - 30))||(myTankX > enemyTankX + 30))||(enemyTankY <= myTankY)||(enemyTankY > (myTankY + 30))){
					validMove = false;
				}
				else{
					enemyCurrentPosition = "SHOOT UP";
					validMove = true;
					commandSequenceEnemy.append("\n");
					commandSequenceEnemy.append("Shoot Up");
					}

				}
				else if(enemyMove == 6){
				if(((myTankX < (enemyTankX - 30))||(myTankX > enemyTankX + 30))||(enemyTankY >= myTankY)||(enemyTankY < (myTankY - 30))){
					validMove = false;
				}
				else{
					enemyCurrentPosition = "SHOOT DOWN";
					validMove = true;
					commandSequenceEnemy.append("\n");
					commandSequenceEnemy.append("Shoot Down");
					}

				}
				else if(enemyMove == 7){
				if(((myTankY < (enemyTankY - 30))||(myTankY > enemyTankY + 30))||(enemyTankX <= myTankX)||(enemyTankX > (myTankX + 30))){
					validMove = false;
				}
				else{
					enemyCurrentPosition = "SHOOT LEFT";
					validMove = true;
					commandSequenceEnemy.append("\n");
					commandSequenceEnemy.append("Shoot Left");
					}

				}
				else if(enemyMove == 8){
				if(((myTankY < (enemyTankY - 30))||(myTankY > enemyTankY + 30))||(enemyTankX >= myTankX)||(enemyTankX < (myTankX - 30))){
					validMove = false;
				}
				else{
					enemyCurrentPosition = "SHOOT RIGHT";
					validMove = true;
					commandSequenceEnemy.append("\n");
					commandSequenceEnemy.append("Shoot Right");
					}

				}




			}

		}
					repaint();
					Thread.sleep(1000);


					winner = checkWinner();
						if((winner == "Player")||(winner == "Enemy")||(winner == "Draw")){
							gameOver(winner);
							endOfGame = true;
							i = 17;
							startGame.setEnabled(true);
							loadGame.setEnabled(true);

						}







		}
				listOfCommand.clear();
				if(!endOfGame){
					roundNumber++;
					JOptionPane.showMessageDialog(null, "Round: " + roundNumber, "Next Round", JOptionPane.INFORMATION_MESSAGE);
					upMove.setEnabled(true);
					downMove.setEnabled(true);
					leftMove.setEnabled(true);
					rightMove.setEnabled(true);
					shootUp.setEnabled(true);
					shootDown.setEnabled(true);
					shootLeft.setEnabled(true);
					shootRight.setEnabled(true);
					startMove.setEnabled(true);
					undoMove.setEnabled(true);
					startGame.setEnabled(true);
					saveGame.setEnabled(true);
					loadGame.setEnabled(true);
					moveLeft = 18;
					remainingMove.setText("Move Left: " + moveLeft);
					nextRound = true;
					repaint();
				}
					}

		catch(Exception e){
						e.printStackTrace();
												}
				}


	   	});
		t1.start();
		}
		catch(Exception e){
						e.printStackTrace();
												}








	  }
	  public String checkWinner(){		//[LIKWAN]								//Checking whether there's a winner found for every move

		  String winner = "Pending";
		  if(myTankX == enemyTankX){
				if(myTankY == (enemyTankY - 30)){
					if((myCurrentPosition == "SHOOT DOWN")&&(enemyCurrentPosition != "SHOOT UP")){
						winner = "Player";

					}
					else if((myCurrentPosition != "SHOOT DOWN")&&(enemyCurrentPosition == "SHOOT UP")){
						winner = "Enemy";
					}
					else if((myCurrentPosition == "SHOOT DOWN")&&(enemyCurrentPosition == "SHOOT UP")){
						winner = "Draw";
					}


			  }
				else if(myTankY == (enemyTankY + 30)){
				    if((myCurrentPosition == "SHOOT UP")&&(enemyCurrentPosition != "SHOOT DOWN")){
						winner = "Player";
					}
					else if((myCurrentPosition != "SHOOT UP")&&(enemyCurrentPosition == "SHOOT DOWN")){
						winner = "Enemy";
					}
					else if((myCurrentPosition == "SHOOT UP")&&(enemyCurrentPosition == "SHOOT DOWN")){
						winner = "Draw";
					}

			  }

		  }
		  else if(myTankY == enemyTankY){
				if(myTankX == (enemyTankX + 30)){
					if((myCurrentPosition == "SHOOT LEFT")&&(enemyCurrentPosition != "SHOOT RIGHT")){
						winner = "Player";
					}
					else if((myCurrentPosition != "SHOOT LEFT")&&(enemyCurrentPosition == "SHOOT RIGHT")){
						winner = "Enemy";
					}
					else if((myCurrentPosition == "SHOOT LEFT")&&(enemyCurrentPosition == "SHOOT RIGHT")){
						winner = "Draw";
					}

			  }
				else if(myTankX  == (enemyTankX - 30)){
					if((myCurrentPosition == "SHOOT RIGHT")&&(enemyCurrentPosition != "SHOOT LEFT")){
						winner = "Player";
					}
					else if((myCurrentPosition != "SHOOT RIGHT")&&(enemyCurrentPosition == "SHOOT LEFT")){
						winner = "Enemy";
					}
					else if((myCurrentPosition == "SHOOT RIGHT")&&(enemyCurrentPosition == "SHOOT LEFT")){
						winner = "Draw";
					}

				}
		  }
		  return winner;
	  }


	  public boolean checkRecord(String writtenDifficulty, int round){	//[Christopher]			//Determine whether the winning player has broke the record
			if(writtenDifficulty == "Easy"){
				if(roundNumber < recordNumberEasy){
					newRecord(writtenDifficulty, roundNumber);
					return true;
				}
			else{
					return false;
			}
			}
			else if(writtenDifficulty == "Hard"){
				if(roundNumber < recordNumberHard){
					newRecord(writtenDifficulty, roundNumber);
					return true;

				}
				else{
					return false;
			}
		  }
		  return false;
	  }
	  public void newRecord(String difficulty, int round){						//Updating the newest record
		 try{
			if(difficulty == "Easy"){
				URL path = Tanks.class.getResource("recordEasy.txt");
				File f = new File(path.getFile());
				BufferedWriter bWriter = new BufferedWriter(new FileWriter(f));
				bWriter.write(Integer.toString(round));
				bWriter.close();
				getRecord();
				record.setText("Current Record - Easy: " + recordNumberEasy + " Hard: " + recordNumberHard);
			}
			else if(difficulty == "Hard"){
				URL path = Tanks.class.getResource("recordHard.txt");
				File f = new File(path.getFile());
				BufferedWriter bWriter = new BufferedWriter(new FileWriter(f));
				bWriter.write(Integer.toString(round));
				bWriter.close();
				getRecord();
				record.setText("Current Record - Easy: " + recordNumberEasy + " Hard: " + recordNumberHard);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

	  }


	public void gameOver(String winner){		//[Christopher]							//Announcement of the result of the game
		if(winner == "Player"){
			JOptionPane.showMessageDialog(null, "You win!! Total Round: " + roundNumber, "Game Over", JOptionPane.INFORMATION_MESSAGE);
			if(checkRecord(difficulty, roundNumber)){
				JOptionPane.showMessageDialog(null, "You broke new record: " + roundNumber, "Congradulations!!", JOptionPane.INFORMATION_MESSAGE);
			}

		}
		else if(winner == "Enemy"){
			JOptionPane.showMessageDialog(null, "You lose!!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

		}
		else if(winner == "Draw"){
			JOptionPane.showMessageDialog(null, "It is a draw!!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

		}
		upMove.setEnabled(false);
		downMove.setEnabled(false);
		leftMove.setEnabled(false);
		rightMove.setEnabled(false);
		shootUp.setEnabled(false);
		shootDown.setEnabled(false);
		shootLeft.setEnabled(false);
		shootRight.setEnabled(false);
		startMove.setEnabled(false);
		undoMove.setEnabled(false);
		saveGame.setEnabled(false);
		commandMessage.setText("Winner: " + winner);
	  }



	  public void paintComponent(Graphics g) {		//[Christopher]								//Method for drawing the layout of the game


         String myTank = "myTankDown.jpg";
		 String enemyTank = "enemyTankUp.jpg";

         g.setColor(Color.black);
         g.drawRect(0,0,getSize().width-1,getSize().height-1);
         g.drawRect(1,1,getSize().width-3,getSize().height-3);



         for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
               if ( row % 2 == col % 2 )
                  g.setColor(Color.LIGHT_GRAY);
               else
                  g.setColor(Color.GRAY);
               g.fillRect(2 + col*30, 2 + row*30, 30, 30);

            }
         }


        if(myCurrentPosition == "GO UP"){
			myTank = "myTankUp.jpg";
		}
		else if(myCurrentPosition == "GO DOWN"){
			myTank = "myTankDown.jpg";
		}
		else if(myCurrentPosition == "GO LEFT"){
			myTank = "myTankLeft.jpg";
		}
		else if(myCurrentPosition == "GO RIGHT"){
			myTank = "myTankRight.jpg";
		}
		else if(myCurrentPosition == "SHOOT UP"){
			myTank = "myTankShootUp.jpg";
		}
		else if(myCurrentPosition == "SHOOT DOWN"){
			myTank = "myTankShootDown.jpg";
		}
        else if(myCurrentPosition == "SHOOT LEFT"){
			myTank = "myTankShootLeft.jpg";
		}
		else if(myCurrentPosition == "SHOOT RIGHT"){
			myTank = "myTankShootRight.jpg";
		}


		if(enemyCurrentPosition == "GO UP"){
			enemyTank = "enemyTankUp.jpg";
		}
		else if(enemyCurrentPosition == "GO DOWN"){
			enemyTank = "enemyTankDown.jpg";
		}
        else if(enemyCurrentPosition == "GO LEFT"){
			enemyTank = "enemyTankLeft.jpg";
		}
		else if(enemyCurrentPosition == "GO RIGHT"){
			enemyTank = "enemyTankRight.jpg";
		}
		else if(enemyCurrentPosition == "SHOOT UP"){
			enemyTank = "enemyTankShootUp.jpg";
		}
		else if(enemyCurrentPosition == "SHOOT DOWN"){
			enemyTank = "enemyTankShootDown.jpg";
		}
		else if(enemyCurrentPosition == "SHOOT LEFT"){
			enemyTank = "enemyTankShootLeft.jpg";
		}
		else if(enemyCurrentPosition == "SHOOT RIGHT"){
			enemyTank = "enemyTankShootRight.jpg";
		}

		Image muh = getToolkit().getImage(myTank);
		 g.drawImage(muh,myTankX,myTankY,30,30,this);
		muh = getToolkit().getImage(enemyTank);
		 g.drawImage(muh,enemyTankX,enemyTankY,30,30,this);

		roundMessage.setText("Round: " + roundNumber);
		 }




   }






}
