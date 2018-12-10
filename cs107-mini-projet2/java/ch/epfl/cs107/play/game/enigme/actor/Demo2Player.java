/*
 *	Author:      Aman Bansal
 *	Date:        28 nov. 2018
 */

package ch.epfl.cs107.play.game.enigme.actor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.area.demo2.Demo2Area;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Demo2Player extends MovableAreaEntity {

	private boolean passingDoor;
	private Sprite image;

	// Animation duration in frame number
	private final static int ANIMATION_DURATION = 8;
	
	public Demo2Player(Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		image = new Sprite("ghost.1", 1, 1.f, this);
		passingDoor = ((Demo2Area)getOwnerArea()).isOnDoor(getCurrentMainCellCoordinates());
	}
	
	public Demo2Player(Area area, DiscreteCoordinates position) {
		super(area, Orientation.DOWN, position);
		image = new Sprite("ghost.1", 1, 1.f, this);
		passingDoor = ((Demo2Area)getOwnerArea()).isOnDoor(getCurrentMainCellCoordinates());
	}
	
	public boolean isPassingDoor() {
		return passingDoor;
	}
	
	public void	enterArea(Area area , DiscreteCoordinates position) {
		area.registerActor(this);
		area.enterAreaCells(this, Arrays.asList(position));
		setCurrentPosition(position.toVector());
		this.resetMotion();
		this.setOwnerArea(area);
	}

	public void leaveArea(Area area, DiscreteCoordinates position) {
		area.unregisterActor(this);
		area.leaveAreaCells(this, Arrays.asList(position));
		this.resetMotion();
		this.setOwnerArea(null);
	}
	
	@Override
	public void update(float deltaTime) {
		if (((Demo2Area)getOwnerArea()).isOnDoor(getCurrentMainCellCoordinates())) {
			passingDoor = true;
		} else {
			passingDoor = false;
		}
		
		Keyboard keyboard = getOwnerArea().getKeyboard();
		
		Button leftArrow = keyboard.get(Keyboard.LEFT);
		Button rightArrow = keyboard.get(Keyboard.RIGHT);
		Button upArrow = keyboard.get(Keyboard.UP);
		Button downArrow = keyboard.get(Keyboard.DOWN);
		
		if (isMoving()) {
			super.update(deltaTime);
		} else if (leftArrow.isDown()) {
			if (this.getOrientation().equals(Orientation.LEFT)) {
				if (move(ANIMATION_DURATION)) {
					super.update(deltaTime);
				}
			} else {
				this.setOrientation(Orientation.LEFT);
			}
		} else if (rightArrow.isDown()) {
			if (this.getOrientation().equals(Orientation.RIGHT)) {
				if (move(ANIMATION_DURATION)) {
					super.update(deltaTime);
				}
			} else {
				this.setOrientation(Orientation.RIGHT);
			}
		} else if (upArrow.isDown()) {
			if (this.getOrientation().equals(Orientation.UP)) {
				if (move(ANIMATION_DURATION)) {
					super.update(deltaTime);
				}
			} else {
				this.setOrientation(Orientation.UP);
			}
		} else if (downArrow.isDown()) {
			if (this.getOrientation().equals(Orientation.DOWN)) {
				if (move(ANIMATION_DURATION)) {
					super.update(deltaTime);
				}
			} else {
				this.setOrientation(Orientation.DOWN);
			}
		}
	}
	
	private DiscreteCoordinates getCurrentMainCellCoordinate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPassingDoor(boolean bool) {
		this.passingDoor = bool;
	}
	
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
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
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		image.draw(canvas);
	}
	
	public void setCurrentPosition(Vector v) {
		super.setCurrentPosition(v);
	}
	
	public Area getOwnerArea() {
		return super.getOwnerArea();
	}
}