class BFS {
    //adjacency matrix of cities and distances
    int[][] distances;
    String[] cities;
    //constructor
    public BFS(int[][] distances, String[] cities) {
        this.distances = distances;
        this.cities = cities;
    }
    public Result findShortestPath(int startIndex, int endIndex) {
        //if the city visited the value of that index is true
        boolean[] visited = new boolean[distances.length];
        //queue data structure used for BFS
        CustomQueue<Integer> queue = new CustomQueue<>();
        //distance from beginning city to all other cities, it is default maximum
        int[] distanceFromStart = new int[distances.length];
        //contains which city has been reached from which city
        int[] parents = new int[distances.length];
        //setting the default values
        for (int i = 0; i < distances.length; i++) {
            distanceFromStart[i] = Integer.MAX_VALUE;
            parents[i] = -1;
        }
        //starting city is index 0. added to the queue and visited.
        distanceFromStart[startIndex] = 0;
        queue.enqueue(startIndex);
        visited[startIndex] = true;
        //runs until the queue is empty. checks the neighbours and if its not 9999, calculates the distance. if its shorter than the previous road distanceFromStart and parents updated.
        while (!queue.isEmpty()) {
            int current = queue.dequeue();
            for (int i = 0; i < distances.length; i++) {
                if (distances[current][i] != 99999 && !visited[i]) {
                    int newDistance = distanceFromStart[current] + distances[current][i];
                    if (newDistance < distanceFromStart[i]) {
                        distanceFromStart[i] = newDistance;
                        parents[i] = current;
                    }
                    queue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        return buildResult(startIndex, endIndex, parents, distanceFromStart);
    }
    //used to build and display the results
    private Result buildResult(int startIndex, int endIndex, int[] parents, int[] distances) {
        if (distances[endIndex] == Integer.MAX_VALUE) {
            return new Result(null, -1);
        }
        String[] path = new String[cities.length];
        int pathIndex = 0;
        int current = endIndex;
        while (current != -1) {
            path[pathIndex++] = cities[current];
            current = parents[current];
        }
        StringBuilder resultPath = new StringBuilder();
        for (int i = pathIndex - 1; i >= 0; i--) {
            resultPath.append(path[i]);
            if (i != 0) {
                resultPath.append(" -> ");
            }
        }
        return new Result(resultPath.toString(), distances[endIndex]);
    }
}


