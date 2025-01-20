class CSVReader {
    String[] cities;
    //2D distances array to store distances between cities
    int[][] distances;
    int cityCount;
    //constructor that reads a CSV file from the given filePath and initializes the cities and distances arrays
    public CSVReader(String filePath) {
        try {
            //FileReader is used to open the located file
            //BufferedReader reader object is created to read the file line by line
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(filePath));
            //first line is read, and it is split by commas to extract the column headers
            String line = reader.readLine();
            String[] headers = line.split(",");
            //number of columns
            cityCount = headers.length - 1;
            //arrays created based on the number of the distances and cities
            cities = new String[cityCount];
            distances = new int[cityCount][cityCount];
            //fills the cities array using a for loop, trims the white spaces while doing that
            for (int i = 1; i <= cityCount; i++) {
                cities[i - 1] = headers[i].trim();
            }
            int row = 0;
            //fills the 2D distances array using a while loop that reads the file line by line and splits the elements of the line by comma. Then stores those parts of the line in a parts array. After that in a for loop writes those values in the distances array.
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                for (int col = 1; col <= cityCount; col++) {
                    distances[row][col - 1] = Integer.parseInt(parts[col].trim());
                }
                row++;
            }
            //closes the BufferedReader
            reader.close();
        } catch (Exception e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }
    //method for finding the index of the given city
    public int getCityIndex(String city) {
        for (int i = 0; i < cityCount; i++) {
            if (cities[i].equals(city)) {
                return i;
            }
        }
        return -1;
    }
    public String[] getCities() {
        return cities;
    }
    public int[][] getDistances() {
        return distances;
    }
}
