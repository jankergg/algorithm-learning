import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {
    // finds all line segments containing 4 points
    private final Point[] points;
    private final int len;
    private int lines;
    private LineSegment[] segObjects;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        lines = 0;
        len = points.length;
        // the maximum possibility of LineSegment
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
        this.points = points;
        calcLineSegment();
    }

    private void calcLineSegment() {
        segObjects = new LineSegment[len];
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    for (int l = k + 1; l < len; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            // set a new array of points, and get them sorted
                            Point[] sags = new Point[]{p, q, r, s};
                            Arrays.sort(sags);
                            segObjects[lines] = new LineSegment(sags[0], sags[3]);
                            lines++;

                            if (segObjects.length == lines) {
                                resizeArray(lines * 2);
                            }
                        }
                    }
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
        System.arraycopy(segObjects, 0, segments, 0, lines);
        return segments;
    }

    private void resizeArray(int capacity) {
        assert capacity >= lines;
        if (capacity > lines) {
            LineSegment[] temp = new LineSegment[capacity];
            System.arraycopy(segObjects, 0, temp, 0, lines);
            segObjects = temp;
        }
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
