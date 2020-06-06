import java.util.Random;

public class FamilyBuilding {
    public static void main(String[] args) {

        int[][][] building = generateBuilding();

        printBuilding(building);

        System.out.println("There are " + numberOfPeopleInBuilding(building) + " people living in this building");

        System.out.println("There are " + numberOfKidsIn3UpperFloors(building) + " kids living in 3 upper floors of this building");
    }

    private static int numberOfKidsIn3UpperFloors(int[][][] building) {
        int numberOfKids = 0;
        for (int i = 0; i < building.length; i++)
            for (int j = 0; j < building[i].length; j++)
                for (int k = 0; k < building[i][j].length/2; k++){
                    if (k*2+1 > 4)
                    numberOfKids++;
                }
        return numberOfKids;
    }

    private static int numberOfPeopleInBuilding(int[][][] building) {
        int numberOfPeople = 0;
        for (int i = 0; i < building.length; i++)
            for (int j = 0; j < building[i].length; j++)
                for (int k = 0; k < building[i][j].length; k++){
                    numberOfPeople++;
                }
        return numberOfPeople;
    }

    private static void printBuilding(int[][][] building) {
        String[] names = {"Emma", "Olivia", "Ava", "Isabella", "Liam", "Noah", "William", "James"};
        for (int i = 0; i < building.length; i++)
            for (int j = 0; j < building[i].length; j++)
                for (int k = 0; k < building[i][j].length/2; k++){
                    System.out.println("Floor #" + i + ", " +
                            "Flat #" + j + ", " +
                            "Family members:" +
                            "Name: " + names[building[i][j][k*2]] + ", age: " + building[i][j][k*2+1]);
                }
    }

    private static int[][][] generateBuilding() {
        Random rnd = new Random();
        int random = 0;
        while (random < 3) {
            random = rnd.nextInt(11);
        }
        int[][][] building = new int[random][][]; // generating random (>3) number of floors
        for (int i = 0; i < building.length; i++){
            random = 0;
            while (random == 0){
                random = rnd.nextInt(6);
            }
            building[i] = new int[random][]; // for each floor generating random number of flats, minimum 1
            for (int j = 0; j < building[i].length; j++){
                // for each flat generating random number of family members
                // generating 2n cells for the family
                // each family member is 2n = name and 2n+1 = age
                // 0,1 = man, 2,3 = woman, 4,5 and 6,7 = kids
                int numberOfFamilyMembers = rnd.nextInt(5);
                building[i][j] = new int [numberOfFamilyMembers*2];
                for (int k = 0; k < numberOfFamilyMembers; k++){
                    building[i][j][k*2] = rnd.nextInt(8); // generating name number - later will take it from the array of names
                    // in case of LGBTQ there is a possibility of two fathers or two moms
                    if (k < 2) {
                        building[i][j][k*2 + 1] = rnd.nextInt(103) + 18; // generating age: making sure it is suitable for parents
                    }
                    else{
                        building[i][j][k*2 + 1] = rnd.nextInt(18);
                    }
                }
            }
        }
        return building;
    }
}
