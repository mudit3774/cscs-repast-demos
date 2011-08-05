package n_bodies.relogo

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.BasePatch;
import repast.simphony.relogo.BaseTurtle;
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
/**
 * Transposed and modified from NetLogo N-Bodies model.
 * @author Rowan Copley
 *
 */
@Plural("Bodies")
class Body extends BaseTurtle {
	def fx  //x-component of force vector
	def fy  //y-component of force vector
	def vx  //x-component of velocity vector
	def vy  //y-component of velocity vector
	def xc  //real x-coordinate (in case particle leaves world)
	def yc  //real y-coordinate 
	def mass
	def center_of_mass_xc  //x-coordinate of the center of mass
	def center_of_mass_yc  //y-coordinate of the center of mass
//	def g  //the gravitational constant
	
	def setup(){
		setxy(randomXcor()/2,randomYcor()/2)
		vx = (Utility.randomFloat(bodiesMaxVelocity))+bodiesVelocityAdd
		vy = (Utility.randomFloat(bodiesMaxVelocity))+bodiesVelocityAdd
		xc = getXcor()
		yc = getYcor()
		fx = 0
		fy = 0
	}
	
	def setup(def xcor, def ycor, def xvel, def yvel){
		def distanceMultiple = randomFloat(1.0)
		setxy(xcor*distanceMultiple,ycor*distanceMultiple)
		vx = xvel
		vy = yvel
		xc = getXcor()
		yc = getYcor()
		fx = 0
		fy = 0
	}
	
	def step(){
//		check_for_collisions()
		update_force()
		update_velocity()
		update_position()
		//something about keeping the particle/body centered?
		//something about fade patches??
	}
	
	def check_for_collisions(){
		if(bodiesHere().size() > 1){
			die()
		}
	}
	
	def update_force(){
		for(Body body : other(bodies())){
			def xd = xc - body.xc
			def yd = yc - body.yc
			def distance = Math.sqrt((xd * xd) + (yd * yd))
			def angle = Math.toRadians(towards(body))
			fx = fx + Math.sin(angle)*((mass * body.mass) / (distance*distance))
			fy = fy + Math.cos(angle)*((mass * body.mass) / (distance*distance))			
		}
	}
	
	def update_velocity(){
		vx = vx + (fx * g / mass)
		vy = vy + (fy * g / mass)
		fx = 0
		fy = 0
		label = (int)(Math.sqrt(vx*vx+vy*vy)*100)
	}
	
	def update_position(){
		xc = xc + vx
		yc = yc + vy
		adjustPosition()	
	}
	
	def adjustPosition(){
		//if the coordinates of the turtle exist
		if(xc > getMinPxcor() && xc < getMaxPxcor() && 
		   yc > getMinPycor() && yc < getMaxPycor()){
			setxy(xc,yc)
			showTurtle()
//			label = (int)xc+","+(int)yc+"::"+getMaxPxcor()+","+getMaxPycor()
		}else{
			hideTurtle()
		}
	}
	
	def sum_its_force_on_me(Body body){
		//MATH!
		def xd = this.xc - body.xc
		def yd = this.yc - body.yc
		def distance = Math.sqrt((xd * xd) + (yd * yd))
//		label = "" + distance
		//we're setting body.fx because this is the object that we're going to move
		body.fx =  (this.mass * body.mass) / distance*distance
		body.fy =  (this.mass * body.mass) / distance*distance
	}
	
	def find_center_of_mass(){
		if(!bodies().isEmpty()){
			def tempx
			def tempy
			for(Body body : bodies()){
				continue
			}
		}
	}
}
