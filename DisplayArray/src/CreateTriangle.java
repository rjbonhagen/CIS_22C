public class CreateTriangle {
    private static int count = 1;

    public static void createTriangle(char c, int b) {
        // Base case to stop recursion
        if (count > b) {
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.print(c);
        }
        System.out.println();

        count++;
        createTriangle(c, b);
    }

    public static void main(String[] args) {
        // Call the static method directly
        createTriangle('*', 10);
    }
}