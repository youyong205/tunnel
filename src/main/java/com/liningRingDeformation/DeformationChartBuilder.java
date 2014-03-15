package com.liningRingDeformation;

public class DeformationChartBuilder {

	private int computeStep(double radius, double major, double monir) {
		double max = Math.max(radius, major);
		max = Math.max(max, monir);
		int value = (int) Math.ceil(1.0 * max / 5);

		if (max <= 8) {
			return 2;
		} else if (max <= 20) {
			return 5;
		} else if (max <= 40) {
			return 10;
		}
		if (value % 10 == 0) {
			return value;
		} else {
			return value + (10 - value % 10);
		}
	}

	public String build(double radius, double major, double minor, int angle, String unit) {
		StringBuilder sb = new StringBuilder();
		int length = 500;
		int step = computeStep(radius, major, minor);
		int newRaidus = (int) (radius * 1.0 / (step * 10) * length);
		int newMajor = (int) (major * 1.0 / (step * 10) * length);
		int newMonir = (int) (minor * 1.0 / (step * 10) * length);

		sb.append(buildHeader(length));
		sb.append(buildGraduation(length, step));
		sb.append(buildCoordinate(length));
		sb.append(buildUnit("(" + unit + ")", length));
		sb.append(buildRound(newRaidus, newRaidus, length));
		sb.append(buildRound(newMajor, newMonir, length, angle));
		sb.append(buildEnd());

		return sb.toString();
	}

	public String buildRound(int major, int minor, int length, int angle) {
		return "	<ellipse cx='" + length / 2 + "' cy='" + length / 2 + "' rx='" + major + "' ry='" + minor + "'"
		      + " fill='none'" + "style='stroke:red;stroke-width:2'  transform='rotate(" + angle + " " + length / 2 + " "
		      + length / 2 + ")' />";

	}

	private String buildRound(int x, int y, int length) {
		return "	<ellipse cx='" + length / 2 + "' cy='" + length / 2 + "' rx='" + x + "' ry='" + y + "'" + " fill='none'"
		      + "style='stroke:rgb(99,99,99);stroke-width:2' />";
	}

	public String buildUnit(String str, int length) {
		int start = length - 40;
		int end = length / 2 + 40;
		return "	<text x='" + start + "' y='" + end + "'>" + str + "</text>";
	}

	public String buildHeader(int length) {
		return "<svg width=\"" + length + "\" height=\"" + length + "\" version=\"1.1\""
		      + "xmlns=\"http://www.w3.org/2000/svg\">";
	}

	public String buildGraduation(int length, int value) {
		int step = length / 10;
		int majorLength = 10;
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < 10; i++) {
			if (i == 5) {
				continue;
			} else {
				sb.append(buildLine(step * i, length / 2 - majorLength, step * i, length / 2));
				sb.append(buildLine(length / 2, step * i, length / 2 + majorLength, step * i));

				if (i < 5) {
					int xtemp = -value * 5 + value * i;
					int ytemp = value * 5 - value * i;

					sb.append(buildText(step * i - majorLength, length / 2 + 2 * majorLength, xtemp));
					sb.append(buildText(length / 2 - 4 * majorLength, step * i, ytemp));
				} else {
					int xtemp = value * (i - 5);
					int ytemp = 0 - value * (i - 5);

					sb.append(buildText(step * i - majorLength, length / 2 + 2 * majorLength, xtemp));
					sb.append(buildText(length / 2 - 4 * majorLength, step * i, ytemp));
				}
			}
		}
		return sb.toString();
	}

	public String buildEnd() {
		return "</svg>";
	}

	public String buildText(int start, int end, int value) {
		return "<text x='" + start + "' y='" + end + "'>" + value + "</text>";
	}

	public String buildCoordinate(int length) {
		StringBuilder sb = new StringBuilder();
		int majorLength = 10;
		int minorLength = 7;

		sb.append(buildLine(0, length / 2, length, length / 2));
		sb.append(buildLine(length / 2, 0, length / 2, length));

		sb.append(buildLine(length - majorLength, length / 2 + minorLength, length, length / 2));
		sb.append(buildLine(length - majorLength, length / 2 - minorLength, length, length / 2));
		sb.append(buildLine(length / 2 + minorLength, majorLength, length / 2, 0));
		sb.append(buildLine(length / 2 - minorLength, majorLength, length / 2, 0));

		return sb.toString();
	}

	public String buildLine(int startX, int startY, int endX, int endY) {
		return " <line x1='" + startX + "' y1='" + startY + "' x2='" + endX + "' y2='" + endY + "'"
		      + "style='stroke:rgb(0,0,100);stroke-width:2'/>";
	}
}
