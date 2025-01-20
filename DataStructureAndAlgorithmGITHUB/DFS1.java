class DFS {
    //adjacency matrix of cities and distances
    int[][] distances;
    String[] cities;
    //constructor
    public DFS(int[][] distances, String[] cities) {
        this.distances = distances;
        this.cities = cities;
    }
    public Result findShortestPath(int startIndex, int endIndex) {
        //if city visited, that index turns true
        boolean[] visited = new boolean[distances.length];
        //we are using stuck data structure for DFS
        CustomStack<Integer> stack = new CustomStack<>();
        //values of distances for each city from the starting city
        int[] distanceFromStart = new int[distances.length];
        //previous cities array
        int[] parents = new int[distances.length];
        //at the beginning all distances set to maximum and all parents set to -1
        for (int i = 0; i < distances.length; i++) {
            distanceFromStart[i] = Integer.MAX_VALUE;
            parents[i] = -1;
        }
        distanceFromStart[startIndex] = 0;
        stack.push(startIndex);
        //runs until the stack is empty. all the neighbours are checked. if a node is not 9999 and it is not visited, then calculates that new distance. and if that road is shorter than the before road, newDistance and parents updated.
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (visited[current]) continue;
            visited[current] = true;
            for (int i = 0; i < distances.length; i++) {
                if (distances[current][i] != 99999 && !visited[i]) {
                    int newDistance = distanceFromStart[current] + distances[current][i];
                    if (newDistance < distanceFromStart[i]) {
                        distanceFromStart[i] = newDistance;
                        parents[i] = current;
                    }
                    stack.push(i);
                }
            }
        }
        return buildResult(startIndex, endIndex, parents, distanceFromStart);
    }
    //used to build the result
    private Result buildResult(int startIndex, int endIndex, int[] parents, int[] distances) {
        if (distances[endIndex] == Integer.MAX_VALUE) {
            return new Result(null, -1);
        }
        String[] path = new String[cities.length];
        int pathIndex = 0;
        int current = endIndex;
        //going backwards within the parents array and adds cities to the array
        while (current != -1) {
            path[pathIndex++] = cities[current];
            current = parents[current];
        }
        //formatting the road for readability
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
//contains the path that we get from DFS and the distance as kilometer
class Result {
    String path;
    int distance;
    public Result(String path, int distance) {
        this.path = path;
        this.distance = distance;
    }
    public String getPath() {
        return path;
    }
    public int getDistance() {
        return distance;
    }
}
