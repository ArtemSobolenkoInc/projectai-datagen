# Synthetic Data Generator

This project generates synthetic test data for a fictitious movie database. The data includes a list of movies and their associated credits. It generates two CSV files: `movies.csv` and `credits.csv`.

## Prerequisites

- Java Development Kit (JDK) 17
- Gradle 7.0 or above

The project uses the following dependencies:

- JavaFaker
- OpenCSV

These dependencies are specified in the `build.gradle` file and will be downloaded automatically by Gradle.

## Running the project

To run the project, follow the steps below:

1. Clone this repository to your local machine.

2. Open a terminal and navigate to the root directory of the project (where the `build.gradle` file is located).

3. Run the following command to build the project:

   ```bash
   gradle build
   
4. Run the project using the following command:  
   gradle run

After running the project, you should find two CSV files (movies.csv and credits.csv) in the result_data directory.

## Directory structure
The main Java file (DataGenerator.java) is located in the src/main/java directory.

The output CSV files are written to the result_data directory.


Please adjust the instructions and directory structure section as necessary based on your project setup.
