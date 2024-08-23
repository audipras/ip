import java.util.Scanner;

public class Maga {
    public static abstract class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X] " : "[ ] "); // mark done task with X
        }

        public void markAsDone() {
            isDone = true;
        }

        public void markAsUndone() {
            isDone = false;
        }

        public String getDescription() {
            return description;
        }

        public abstract String getTaskType();
    }

    public static class TodoTask extends Task {
        public TodoTask(String description) {
            super(description);
        }

        public String getTaskType() {
            return "[T]";
        }
    }
    public static class EventTask extends Task {
        protected String time;

        public EventTask(String description, String time) {
            super(description);
            this.time = time;
        }

        public String getTaskType() {
            return "[E]";
        }
    }
    public static class DeadlineTask extends Task{
        protected String from;
        protected String to;

        public DeadlineTask(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        public String getTaskType() {
            return "[D]";
        }
    }
    public static void main(String[] args) {
        String logo = "  __  __                    \n"
                + " |  \\/  |  __ _   __ _   __ _  \n"
                + " | |\\/| | / _` | / _` | / _` | \n"
                + " | |  | || (_| || (_| || (_| || \n"
                + " |_|  |_| \\__,_| \\__, | \\__,_|  \n"
                + "                  |___/                           \n";
        System.out.println("Hello from\n" + logo +"\nI am THE best chatbot from the one and only" +
                " US of A trust me everyone says I'm the best. How can I help you serve the American people?" );
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // task array
        Task[] arr = new Task[100];
        int count = 0;
        while(!input.equalsIgnoreCase("bye")) {
            // using list command
            input = input.toLowerCase();
            if(input.equals("list")) {
                System.out.println("Take a look, all the tasks you have here, so many, yuuuuuuge\n");
                for (int i = 0; i < count; i++) {
                    int temp = i + 1;
                    System.out.println(temp + ". " + arr[i].getTaskType() + arr[i].getStatusIcon()
                            + arr[i].getDescription());
                }

                input = scanner.nextLine();
                continue;
            }

            // marking things as done and undone
            if (input.startsWith("mark ")) {
                System.out.println("Ya boi Donald took the LIBERTY to mark this done:\n");
                char[] charArray = input.toCharArray();
                Task temp = arr[Character.getNumericValue(charArray[charArray.length - 1]) - 1];
                if (temp == null) {
                    System.out.println("You're trying to mark a task that DOESN'T EXIST, like bad people on JAN 6. " +
                            "Some of the kindest and most lovely souls I've met");
                    input = scanner.nextLine();
                    continue;
                }
                temp.markAsDone();
                System.out.println(temp.getTaskType() + temp.getStatusIcon() + temp.getDescription());
                input = scanner.nextLine();
                continue;
            }

            if (input.toLowerCase().startsWith("unmark ")) {
                System.out.println("Here's the task promised but not completed, just like the DEMS\n");
                char[] charArray = input.toCharArray();
                Task temp = arr[Character.getNumericValue(charArray[charArray.length - 1]) - 1];
                if (temp == null) {
                    System.out.println("Stop trying to unmark tasks like ILLEGAL ALIENS after" +
                            " I'm president: NOT HERE!");
                    input = scanner.nextLine();
                    continue;
                }
                temp.markAsUndone();
                System.out.println(temp.getStatusIcon() + temp.getDescription());
                input = scanner.nextLine();
                continue;
            }

            // deleting things
            if(input.startsWith("delete")) {
                String descrip = input.substring(7).trim();
                int tempInt;
                try {
                    tempInt = Integer.parseInt(descrip);
                } catch (Exception NumberFormatException) {
                    System.out.println("You can only delete a task number! No one calls amendments by their names!!");
                    input = scanner.nextLine();
                    continue;
                }

                Task tempTask = arr[tempInt - 1];
                count--;
                System.out.print("I've deleted this task:\n" + tempTask.getTaskType() + tempTask.getStatusIcon() +
                        tempTask.getDescription() + "\nYou have " + count + " task(s) now!\n");
                for (int i = count; i < 99; i++) {
                    arr[i + 1] = arr[i];
                }
                input = scanner.nextLine();
                continue;

            }

            // adding things as per normal
            Task tempTask = new TodoTask("");
            if(input.startsWith("todo")) {
                String descrip = input.substring(5).trim();
                tempTask = new TodoTask(descrip);
            } else if(input.startsWith("event")) {
                String descrip = input.substring(6).trim();
                String[] descripArray = descrip.split("/");
                if (descripArray.length != 2) {
                    System.out.println("An event needs a date!! Don't be Crooked Kamala!!");
                    input = scanner.nextLine();
                    continue;
                }
                tempTask = new EventTask(descripArray[0], descripArray[1]);
            } else if(input.startsWith("deadline")) {
                String descrip = input.substring(9).trim();
                String[] descripArray = descrip.split("/");
                if (descripArray.length != 3) {
                    System.out.println("A deadline needs a start and end!! Filibusters are a threat to the " +
                            "American people!!");
                    input = scanner.nextLine();
                    continue;
                }
                tempTask = new DeadlineTask(descripArray[0], descripArray[1], descripArray[2]);
            } else { // if it's not mark, list, delete or creating a task
                System.out.println("HEY! SLEEPY JOE and CROOKED KAMALA " +
                        "might be demented but you're not! Specify a command!");
                input = scanner.nextLine();
                continue;
            }


            arr[count] = tempTask;
            count++;
            System.out.println("Another task for the American people added:\n" + tempTask.getTaskType()
            + tempTask.getStatusIcon() + tempTask.getDescription() + "\nYou have " + count + " task(s) now!");
            input = scanner.nextLine();
        }

        System.out.println("Yeah I'ma see you in my next RALLY! A vote for me is a vote for America!");
    }

}
