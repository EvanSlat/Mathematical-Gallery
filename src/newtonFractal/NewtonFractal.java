package newtonFractal;

import java.awt.Graphics2D;

import auxilory.IPoint;
import auxilory.MathFunction;
import auxilory.Point;

public class NewtonFractal implements MathFunction {

	@Override
	public void drawFrame(Graphics2D g, int timeStep) {
		// TODO Auto-generated method stub

	}

	
	
	private class NPoint extends IPoint{
		Point originalP;
		int xPixel, yPixel;
		Point[] roots;
		public NPoint(double r, double i, int xP, int yP) {
			super(r, i);
			this.xPixel = xP;
			this.yPixel = yP;
		}
		
		
		
		//TODO make this recrusive
		private IPoint calcFunc() {
			return reCalcFunc(roots.length);
		}
		
		private IPoint reCalcFunc(int i) {
			if(i == -1) {
				return new IPoint(1,0);
			}else {
				//does not work
				return reCalcFunc(i-1).mult((IPoint) new IPoint(x, y).subtract(roots[i]));
			}
		}
		
		//TODO make this recrusive
		private IPoint calcDerivative() {
			return reCalcDeri(roots.length);
		}
		
		private IPoint reCalcDeri(int i) {
			if(i == -1) {
				return new IPoint(0, 0);
			}else {				
				IPoint left = reCalcFunc(i-1);
				IPoint right = reCalcDeri(i-1).mult((IPoint) new IPoint(x, y).subtract(roots[i]));
				return (IPoint) left.add(right);
			}
		}
		
		public void runItteration(int remainingItterations) {
			if(remainingItterations == 0) {
				return;
			}
			subtract(calcFunc().divide(calcDerivative()));
		}
		
	}
}
