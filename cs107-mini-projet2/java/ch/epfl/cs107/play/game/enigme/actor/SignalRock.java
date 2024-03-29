package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class Signal Rock representing a rock dependent on a Logic signal; extends AreaEntity
 * @author Aman Bansal, Julian Blackwell
 */
public class SignalRock extends AreaEntity {

	private Logic signal;
	private Sprite image;
	
	/**
	 * Constructor for a Signal Rock
	 * @param signal (Logic) : the logic signal on which the signal rock depends
	 * @param area (Area) : the area to which the signal rock belongs
	 * @param position (DiscreteCoordaintes) : the position of the signal rock in the area
	 */
	public SignalRock(Logic signal, Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		this.signal = signal;
		this.image = new Sprite("rock.3", 1, 1.f, this);
	}
	
	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return !signal.isOn();
	}

	@Override
	public boolean isViewInteractable() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}
		
	@Override
	public void draw(Canvas canvas) {
		if (!signal.isOn()) {
			image.draw(canvas);
		}
	}
}