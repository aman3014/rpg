package ch.epfl.cs107.play.game.enigme.area.levels;

import java.util.List;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.MovingMob;
import ch.epfl.cs107.play.game.enigme.actor.Sage;
import ch.epfl.cs107.play.game.enigme.actor.SignalDoor;
import ch.epfl.cs107.play.game.enigme.actor.SignalRock;
import ch.epfl.cs107.play.game.enigme.actor.collectable.Egg;
import ch.epfl.cs107.play.game.enigme.actor.collectable.Key;
import ch.epfl.cs107.play.game.enigme.actor.SignalBonfire;
import ch.epfl.cs107.play.game.enigme.actor.SignalSage;
import ch.epfl.cs107.play.game.enigme.area.EnigmeArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logicGates.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

/**
 * Enigme 0 "Room/Level" (instance of EnigmeArea)
 * @author Julian Blackwell, Aman Bansal
 *
 */
public class Enigme0 extends EnigmeArea {
	
	//Actors "living/playing" in the area
	private Actor doorToLevelSelector;
	private Actor egg;
	private Actor signalRock;
	private Actor bonfire;
	private Actor key;
	private Actor sageIntro, sageExit;
	private Actor sageNorth, sageEast, sageSouth, sageWest;
	private Actor mob;
	
	@Override
	public String getTitle() {
		return "Enigme0";
	}
	
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		
		//Initialize actors
		
		key = new Key(this, new DiscreteCoordinates(28, 1));
		
		DiscreteCoordinates position = new DiscreteCoordinates(4, 0);
		doorToLevelSelector = new SignalDoor((Logic) key, this, "LevelSelector", new DiscreteCoordinates(5, 6), position, position);
		
		sageIntro = new Sage(this, Orientation.UP, new DiscreteCoordinates(10, 24), "The key to the door is found on a far away land. And Remember! Helping others on your way is the key to getting there !");
		
		sageNorth = new SignalSage(this, Orientation.LEFT, new DiscreteCoordinates(6, 14), "I seek the star that guided the pirates...", Orientation.UP);
		sageEast = new SignalSage(this, Orientation.DOWN, new DiscreteCoordinates(4, 16), "I seek a glimpse of dawn...", Orientation.RIGHT);
		sageSouth = new SignalSage(this, Orientation.UP, new DiscreteCoordinates(6, 18), "Help me face the warmth of family...", Orientation.DOWN);
		sageWest = new SignalSage(this, Orientation.RIGHT, new DiscreteCoordinates(8, 16), "I seek the crimson eye before twilight...", Orientation.LEFT);
		bonfire = new SignalBonfire(this, new DiscreteCoordinates(6, 16), false, new MultipleAnd((Logic)sageNorth,(Logic)sageEast, (Logic)sageSouth, (Logic)sageWest));
		
		sageExit = new Sage(this, Orientation.LEFT, new DiscreteCoordinates(29, 1), "I have heard of a hidden path amongst the trees ! After all, who doesn't want to return home ?");
		
		egg = new Egg(this, new DiscreteCoordinates(15, 15));
		signalRock = new SignalRock((Logic)bonfire, this, new DiscreteCoordinates(16, 15));
		
		mob = new MovingMob(this, Orientation.DOWN, new DiscreteCoordinates(8, 22));
		
		if (!super.begin(window, fileSystem)) {
			return false;
		}
		
		registerActor(new Foreground(this));
		
		return true;
	}

	@Override
	protected void addAllActors(List<Actor> actors) {
		actors.add(doorToLevelSelector);
		actors.add(egg);
		actors.add(key);
		actors.add(signalRock);
		actors.add(sageIntro);
		actors.add(sageNorth);
		actors.add(sageEast);
		actors.add(sageSouth);
		actors.add(sageWest);
		actors.add(sageExit);
		actors.add(bonfire);
		actors.add(mob);
	}
}