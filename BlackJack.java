import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

class Card {
    private int value;
    private String suit;
    private String rank;

// Constructor
public Card(int value, String suit, String rank) {
    this.value = value;
    this.suit = suit;
    this.rank = rank;
}
// Accessors
public int getValue() {
    return value;
}

public String getSuit() {
    return suit;
}

public String getRank() {
    return rank;
}

// toString method
@Override
public String toString() {
    return rank + " of " + suit;
    }
}

public class BlackJack {

    private static ArrayList<Card> deck = new ArrayList<>();
    private static int currentCardIndex = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean turn = true;

        while(turn) {
            initializeDeck();
            shuffleDeck();
            int playerTotal = 0;
            int dealerTotal = 0;
            playerTotal = dealInitialPlayerCards();
            dealerTotal = dealInitialDealerCards();
            playerTotal = playerTurn(scanner, playerTotal);
            if (playerTotal > 21) {
                System.out.println("You busted! Dealer wins.");
}               else {
                    dealerTotal = dealerTurn(dealerTotal);
                    determineWinner(playerTotal, dealerTotal);

}
                  System.out.println("Would you like to play another hand?");
                  String playerDecision = scanner.nextLine().toLowerCase();
                  while(!(playerDecision.equals("no") || (playerDecision.equals("yes"))))
{

                    System.out.println("Invalid action. Please type 'yes' or 'no'.");
                    playerDecision = scanner.nextLine().toLowerCase();
}

                  if (playerDecision.equals("no"))
                  turn = false;
}
            System.out.println("Thanks for playing!");
            scanner.close();
}

// Initialize deck
private static void initializeDeck() {
    String[] suits = { "Hearts", "Diamonds", "Clubs","Spades" };
    String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9","10", "Jack",
"Queen", "King","Ace" };
    deck.clear();

    for (String suit : suits) {
        for (int i = 0; i < ranks.length; i++) {
           int value;
            if (i < 9) {
            value = i + 2;
            } else if (i < 12) {
            value = 10;
            } else {
            value = 11;
}
        deck.add(new Card(value, suit, ranks[i]));
    }

}
            currentCardIndex = 0;
}
        // Shuffle deck
        private static void shuffleDeck() {
        Collections.shuffle(deck, new Random());
    }

        // Deal initial player cards
        private static int dealInitialPlayerCards() {
            Card card1 = dealCard();
            Card card2 = dealCard();
            System.out.println("Your cards: " + card1 + " and " + card2);
            return card1.getValue() + card2.getValue();
}

        // Deal initial dealer cards
        private static int dealInitialDealerCards() {
            Card card1 = dealCard();
            System.out.println("Dealer's card: " + card1);
            return card1.getValue();
}

        // Player's turn
        private static int playerTurn(Scanner scanner, int playerTotal) {
            while (true) {
            System.out.println("Your total is " + playerTotal + ". Do you want to
            hit or stand?");
            String action = scanner.nextLine().toLowerCase();
            if (action.equals("hit")) {
                Card newCard = dealCard();
                playerTotal += newCard.getValue();
                System.out.println("You drew a " + newCard);
            if (playerTotal > 21) {
                System.out.println("You busted! Dealer wins.");
                return playerTotal;
            }
}             else if (action.equals("stand")) {
                break;
}             else {
                System.out.println("Invalid action. Please type 'hit' or
'stand'.");
    }
}
    return playerTotal;
}
// Dealer's turn
private static int dealerTurn(int dealerTotal) {
    while (dealerTotal < 17) {
        Card newCard = dealCard();
        dealerTotal += newCard.getValue();
}
    System.out.println("Dealer's total is " + dealerTotal);
    return dealerTotal;
}

// Determine winner
private static void determineWinner(int playerTotal, int dealerTotal) {
    if (dealerTotal > 21 || playerTotal > dealerTotal) {
        System.out.println("You win!");
}   else if (dealerTotal == playerTotal) {
        System.out.println("It's a tie!");
}   else {
        System.out.println("Dealer wins!");
    }
}

// Deal a card
private static Card dealCard() {
    return deck.get(currentCardIndex++);
    }
}
