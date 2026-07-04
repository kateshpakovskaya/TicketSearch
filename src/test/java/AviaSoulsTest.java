import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.AviaSouls;
import ru.netology.Ticket;
import ru.netology.TicketTimeComparator;

public class AviaSoulsTest {

    Ticket ticket1 = new Ticket("LED", "KGD", 4500, 12, 14);// цена: 4500, время: 2
    Ticket ticket2 = new Ticket("LED", "KGD", 6000, 16, 17);// цена: 6000, время: 1
    Ticket ticket3 = new Ticket("LED", "KGD", 2500, 19, 22);// цена: 2500, время: 3
    Ticket ticket4 = new Ticket("LED", "MMK", 3000, 10, 12);

    @Test
    public void shouldCompareTicketsByPrice() {
        Assertions.assertTrue(ticket1.compareTo(ticket2) < 0);
        Assertions.assertTrue(ticket2.compareTo(ticket3) > 0);
        Assertions.assertEquals(0, ticket1.compareTo(ticket1));
    }

    @Test
    public void shouldSearchAndSortByPrice() {
        AviaSouls souls = new AviaSouls();
        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);

        Ticket[] expected = {ticket3, ticket1, ticket2};
        Ticket[] actual = souls.search("LED", "KGD");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAndReturnOneTicketByPrice() {
        AviaSouls souls = new AviaSouls();
        souls.add(ticket2);
        souls.add(ticket4);

        Ticket[] expected = {ticket4};
        Ticket[] actual = souls.search("LED", "MMK");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyIfNoTicketsFoundByPrice() {
        AviaSouls souls = new AviaSouls();
        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);

        Ticket[] expected = {};
        Ticket[] actual = souls.search("LED", "PES");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareTicketsByTime() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        Assertions.assertTrue(timeComparator.compare(ticket1, ticket2) > 0);
        Assertions.assertTrue(timeComparator.compare(ticket1, ticket3) < 0);
    }

    @Test
    public void shouldSearchAndSortByTimeUsingComparator() {
        AviaSouls souls = new AviaSouls();
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);

        Ticket[] expected = {ticket2, ticket1, ticket3};
        Ticket[] actual = souls.searchAndSortBy("LED", "KGD", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}
