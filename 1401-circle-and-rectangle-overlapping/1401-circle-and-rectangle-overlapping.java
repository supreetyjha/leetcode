class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        // Clamp the circle's center to the rectangle boundaries
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));
        
        // Compute squared Euclidean distance from center to the closest point
        int dx = closestX - xCenter;
        int dy = closestY - yCenter;
        
        return (dx * dx + dy * dy) <= (radius * radius);
    }
}