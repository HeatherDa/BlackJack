package com.Heather;
        import java.util.*;
public class Main {

    public static void main(String[] args) {
        //
        Scanner scan = new Scanner(System.in);
        HashMap<Integer, Integer> fulldeck = new HashMap<>();
        fulldeck.put(1, 4);   //aces
        fulldeck.put(2, 4);   //twos
        fulldeck.put(3, 4);   //threes
        fulldeck.put(4, 4);   //fours
        fulldeck.put(5, 4);   //fives
        fulldeck.put(6, 4);   //sixs
        fulldeck.put(7, 4);   //sevens
        fulldeck.put(8, 4);   //eights
        fulldeck.put(9, 4);   //nines
        fulldeck.put(10, 4); //tens
        fulldeck.put(11, 2);  //black Jacks
        fulldeck.put(12, 2);  //red Jacks
        fulldeck.put(13, 4);  //Queens
        fulldeck.put(14, 4);  //Kings
        Random card = new Random();
        int playerhand = 0;
        int computerhand = 0;
        int r = 0;
        int topp = 0; //player's visible card value
        int topc = 0; //computer's visible card value
        int total = 0;
        boolean blackjack = false;
        ArrayList<Integer> cardsp = new ArrayList<>();
        ArrayList<Integer> cardsc = new ArrayList<>();
        String dealcard;
        boolean playagain = false;
        while (playagain = true) {
            for (int i = 0; i < 2; i++) { //deal first two cards to player and computer MAKE THIS LOOP A FUNCTION
                //generate random number and assign to player MAKE THIS INTO A FUNCTION
                r = card.nextInt(15 - 0) + 1;
                if ((fulldeck.get(r) != 0) && (r != 11)) {
                    playerhand = playerhand + r;
                    fulldeck.put(r, (fulldeck.get(r) - 1));
                    topp = r;
                    cardsp.add(r);
                } else if (r == 11) {
                    playerhand = playerhand + 10;
                    blackjack = true;
                }else if ((r>= 12)&&(r<=15)){
                    playerhand=playerhand+10;
                }
                //generate random number and assign to computer
                r = card.nextInt(12 - 0) + 1;
                if ((fulldeck.get(r) != 0) && (r != 11)) {
                    computerhand = computerhand + r;
                    fulldeck.put(r, (fulldeck.get(r) - 1));
                    topc = r;
                    cardsc.add(r);
                } else if (r == 11) {
                    playerhand = playerhand + 10;
                    blackjack = true;
                }else if ((r>= 12)&&(r<=15)){
                    computerhand=computerhand+10;
                }
            }
            playagain=blackjack(cardsp,cardsc,blackjack);
            //ask player if they want another card, give if requested
            if (computerhand <= 21) { //not needed?
// while loop for user to get as many cards as they want?
                System.out.println("You have " + playerhand + " points.  The computers visible card is " + topc + ".  The value of each of your cards is" + cardsp);
                System.out.println("Do you want another card? Type y for yes or n for no.");
                dealcard = scan.nextLine();
                if (dealcard.equalsIgnoreCase("yes")) {
                    r = card.nextInt(15 - 0) + 1;
                    if ((fulldeck.get(r) != 0) && (r != 11)) {
                        playerhand = playerhand + r;
                        fulldeck.put(r, (fulldeck.get(r) - 1));
                        topp = r;
                        cardsp.add(r);
                    } else if (r == 11) {
                        playerhand = playerhand + 10;
                        blackjack = true;
                    }
                }
            }
            for (int cardace : cardsp) { //determine value for Ace
                if (cardace == 1) {
                    System.out.println("Do you want your ace to count as a 1 or as an 11? Type the value you wish assigned to it.");
                    int ace11 = scan.nextInt();
                    if (ace11 == 11) ;
                    playerhand = playerhand + 10;
                }
            }
            //decide ace value for computer
//while loop for computer to take cards
            boolean ace=false;
            int othercard=0;
            for (int cardace : cardsc) { //determine value for Ace
                if (cardace != 1) {
                    othercard = cardace;
                } else {
                    ace = true;
                }
            }if ((ace=true)&&(othercard<11)){
                computerhand = computerhand + 11;

            }else{
                computerhand=computerhand+1;
            }

            //calculate average of cards left in deck
            for (int value : fulldeck.values()) {
                total = total + value;
                System.out.println("total is " + total);
            }
            double average = (total - computerhand - topp) / fulldeck.size();
            System.out.println("average is" + average);
            //computer decides if it wants another card
            if ((computerhand < (21 - average)) || (computerhand < (topp + average))) { //check computations
                r = card.nextInt(12 - 0) + 1;
                if ((fulldeck.get(r) != 0) && (r != 11)) {
                    computerhand = computerhand + r;
                    fulldeck.put(r, (fulldeck.get(r) - 1));
                    cardsp.add(r);
                } else if (r == 11) {
                    computerhand = computerhand + 10;
                    blackjack = true;
                }
            }
            playagain=wholost(playerhand, computerhand);
        }
    }
    private static boolean wholost(int playerhand, int computerhand){
        Scanner scan=new Scanner(System.in);
        boolean playagain=true;
        if((playerhand>21)||(computerhand>21)) {
            if (playerhand > 21) {
                System.out.println("I'm sorry, but you have lost this hand.  Do you want to play again? Type y for yes and n for no.");
                if (scan.nextLine().equalsIgnoreCase("n")) {
                    playagain=false;
                }
            } else {
                System.out.println("The computer lost.  Do you want to play again? Type y for yes and n for no.");
                if (scan.nextLine().equalsIgnoreCase("n")) {
                    playagain=false;
                }
            }
        }scan.close();
        return playagain;
    }private static boolean blackjack(ArrayList<Integer> cardsp, ArrayList<Integer> cardsc, boolean blackjack){
        Scanner scan= new Scanner(System.in);
        boolean playagain=true;
        for(int cardace:cardsp) { //determine value for Ace
            if ((cardace == 1) && (blackjack == true)) {
                System.out.println("You have a black Jack and an ace so you win!  Would you like to play again?  Type y for yes and n for no.");
                if ((scan.nextLine()).equalsIgnoreCase("n")) {
                    playagain = false;
                }
            }
        }
        for(int cardace:cardsc) { //determine value for Ace
            if ((cardace == 1) && (blackjack == true)) {
                System.out.println("The computer has a black Jack and an ace so the computer wins!  Would you like to play again?  Type y for yes and n for no.");
                if ((scan.nextLine()).equalsIgnoreCase("n")) {
                    playagain=false;
                }
            }

        }scan.close();
        return playagain;
    }
}
