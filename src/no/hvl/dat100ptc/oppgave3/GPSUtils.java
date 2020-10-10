package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max;

		max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double[] latTab = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			latTab[i] = gpspoints[i].getLatitude();
		}
		return latTab;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		double[] longTab = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			longTab[i] = gpspoints[i].getLongitude();
		}
		return longTab;

	}

	private static int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = toRadians(gpspoint1.getLatitude());
		latitude2 = toRadians(gpspoint2.getLatitude());
		longitude1 = toRadians(gpspoint1.getLongitude());
		longitude2 = toRadians(gpspoint2.getLongitude());
		double latdiff = latitude2 - latitude1;
		double longdiff = longitude2 - longitude1;
		double a = pow(sin(latdiff / 2), 2) + cos(latitude1) * cos(latitude2) * pow(sin(longdiff / 2), 2);
		double c = 2 * atan2(sqrt(a), sqrt(1 - a));
		d = R * c;
		return d;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = (distance(gpspoint1, gpspoint2) / secs) * 3.6;
		return speed;
	}

	public static String formatTime(int secs) {

		String timestr;
		String TIMESEP = ":";

		timestr = "  " +
				String.format("%02d", secs / 3600) + TIMESEP +
				String.format("%02d", (secs % 3600) / 60) + TIMESEP +
				String.format("%02d", (secs % 3600) % 60);
		return timestr;
	}

	private static int TEXTWIDTH = 10;

	public static String formatDouble(double d) {

		String str;
		str = String.format("%,10.2f", d);
		return str;
	}
}
