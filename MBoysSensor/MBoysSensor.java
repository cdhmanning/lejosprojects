import lejos.nxt.*;

public class MBoysSensor {
	public static void main(String[] args) throws Exception {
		I2CSensor sensor = new I2CSensor(SensorPort.S1);
		int poll = 1;
		byte[] data = new byte[5];
		boolean done = false;

		while (!done) {
			LCD.clear();
			LCD.drawString("MBoys ATIO Test",0,0);
			LCD.drawInt(poll++,0,1);
			LCD.drawString(sensor.getProductID(),0,2);
			LCD.drawString(sensor.getSensorType(),0,3);
			LCD.drawString(sensor.getVersion(),0,4);
			
			sensor.sendData(0x40,(byte)0x07);
			sensor.sendData(0x41,(byte)(poll & 0x07));
			
			sensor.getData(0x18,data,5);
			LCD.drawInt(((int)(data[0])) & 0xff,0,5);
			LCD.drawInt(((int)(data[1])) & 0xff,4,5);
			LCD.drawInt(((int)(data[2])) & 0xff,8,5);
			LCD.drawInt(((int)(data[3])) & 0xff,12,5);
			LCD.drawInt(((int)(data[4])) & 0xff,0,6);
			
			LCD.drawInt(SensorPort.S2.readRawValue(),8,6);			
			Thread.sleep(500);
			
		}
	}
	
}
