package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity implements Interactable {
	
	// an AreaEntity knows its own Area
	private Area ownerArea;
	
	// Orientation of the AreaEntity in the Area
	private Orientation orientation;
	
	// Coordinate of the main Cell linked to the entity
	private DiscreteCoordinates currentMainCellCoordinates;
	
    /**
     * Default AreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(position.toVector());
        this.currentMainCellCoordinates = position;
        this.ownerArea = area;
        this.orientation = orientation;
    }
    
    /**
     * Getter for the owner Area of the entity
     * @return ownerArea (Area) : owner Area of entity
     */
    protected Area getOwnerArea() {
    	return ownerArea;
    }

    /**
     * Setter for the owner Area of the entity
     * @param area (Area) : new owner area of entity
     */
    protected void setOwnerArea(Area area) {
    	ownerArea = area;
    }
    
    /**
     * Getter for the coordinates of the main cell occupied by the AreaEntity
     * @return (DiscreteCoordinates)
     */
    protected DiscreteCoordinates getCurrentMainCellCoordinates(){
        return currentMainCellCoordinates;
    }
    
    /**
     * Getter for the orientation of the entity
     * @return orientation (Orientation) : orientation of entity
     */
    protected Orientation getOrientation() {
    	return orientation;
    }
    
    /**
     * Setter for the orientation of the entity
     * @param orientation (Orientation) : new orientation of entity
     */
    protected void setOrientation(Orientation orientation) {
    	this.orientation = orientation;
    }
    
    @Override
    protected void setCurrentPosition(Vector v) {
    	if (DiscreteCoordinates.isCoordinates(v)) {
    		v = v.round();
    		currentMainCellCoordinates = new DiscreteCoordinates((int) v.x, (int) v.y);
    	}
    		super.setCurrentPosition(v);
    }
}