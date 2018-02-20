public class tempTest {
    public static void main(String[] args) {
        String input = "g:b:tristan ";
        String name;
        int number = -9999;
        String action = input.substring(0,input.indexOf(":"));
        input = input.substring(input.indexOf(":")+1);
        String type = input.substring(0, input.indexOf(":"));
        input = input.substring(input.indexOf(":")+1);
        if (input.indexOf(":") != -1) {
            name = input.substring(0, input.indexOf(":"));
            input = input.substring(input.indexOf(":")+1);
            number = Integer.parseInt(input);
        } else {
            name = input;
        }
        System.out.println("action: " + action + "\ntype: " + type);
        if (number != -9999) {
            System.out.println("number: " + number);
        }
        System.out.println("name: " + name);
    }
}