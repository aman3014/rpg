package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Arrays;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

/**
 * Class sage representing a sage (wise old man)
 * extends AreaEntity; implements Talker
 * @author Aman Bansal, Julian Blackwell
 */
public class Sage extends AreaEntity implements Talker {

	private Animation animation;
	private Dialog dialog;
	private Dialog wisdom;
	
	/**
	 * Constructor for a sage
	 * @param area (Area) : the area to which the sage belongs
	 * @param orientation (Orientation) : the initial orientation of the sage
	 * @param position (DiscreteCoordinates) : the position of the sage in the area
	 * @param wisdom (String) : the wisdom/knowledge the sage can share with an interactor
	 */
	public Sage(Area area, Orientation orientation, DiscreteCoordinates position, String wisdom) {
		super(area, orientation, position);
		animation = new Animation(this, new Vector(0.13f, 0.13f), 1.5f, 1, "old.man.1");
		this.wisdom = new Dialog(wisdom, "dialog.1", area);
		this.dialog = new Dialog("Hold the S key to obtain the Sage's wisdom", "dialog.1", area);
		animation.updateAnimationCounter();
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Arrays.asList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((EnigmeInteractionVisitor) v).interactWith(this);
	}

	@Override
	public void draw(Canvas canvas) {
		animation.draw(canvas, getOrientation());
	}
	
	@Override
	public Dialog getDialog() {
		return dialog;
	}
	
	/**
	 * Method returning a dialog with the sage's wisdom
	 * @return (Dialog) : sage's wisdom
	 */
	public Dialog getWisdom() {
		return wisdom;
	}
	
	@Override
	public Orientation getOrientation() {
		return super.getOrientation();
	}
	
	/**
	 * Method used to set the orientation of the sage
	 * @param orientation (Orientation) : the new orientation
	 */
	public void setOrientation(Orientation orientation) {
		super.setOrientation(orientation);
	}
}