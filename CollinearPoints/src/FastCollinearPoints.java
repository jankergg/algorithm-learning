import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    // finds all line segments containing 4 or more points
    private Point[] points;
    private final int len;
    private int lines;
    private LineSegment[] segmentObjects;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        len = points.length;
        // validate the arguments as well as initialize LineSegment
        for (int i = 0; i < len; i++) {
            if (points[i] == null) {
                throw new IllegalArgumentException();
            }
            for (int j = i + 1; j < len; j++) {
                if (points[j] == null || points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated Points!");
                }
            }
        }
        // initialization
        lines = 0;
        // the maximum possibility of LineSegment
        this.points = points;
        segmentObjects = new LineSegment[len];
        calcLineSegment();
    }


    private void calcLineSegment() {
        Point[] pointsCopy = new Point[len];
        System.arraycopy(points, 0, pointsCopy, 0, len);
        Arrays.sort(pointsCopy);

        // make a new Array for slopeOrder sorting

        for (int i = 0; i < len - 3; i++) {
            Point p = pointsCopy[i];
            // copy the whole array except for p itself
            Point[] tmpPoints = new Point[len];
            System.arraycopy(pointsCopy, 0, tmpPoints, 0, i);
            System.arraycopy(pointsCopy, i + 1, tmpPoints, i, len - i - 1);
            Arrays.sort(tmpPoints, 0, len - 1, p.slopeOrder());

            // find out how many items that has equaled slop.
            int cur = 0;
            for (int j = 1; j < len; j++) {
                Point starter = tmpPoints[cur];
                Point current = tmpPoints[j];
                if (current == null || starter.slopeTo(p) != current.slopeTo(p)) {
                    if (j - cur >= 3 && starter != null && starter.compareTo(p) > 0) {
                        segmentObjects[lines] = new LineSegment(p, tmpPoints[j - 1]);
                        lines++;
                    }
                    cur = j;
                }
            }
        }


    }

    // the number of line segments
    public int numberOfSegments() {
        return lines;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[lines];
        System.arraycopy(segmentObjects, 0, segments, 0, lines);
        return segments;
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
