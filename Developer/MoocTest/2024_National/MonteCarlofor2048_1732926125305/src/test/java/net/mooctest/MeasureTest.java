package net.mooctest;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MeasureTest {

    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private static PrintStream originalOut;

    @BeforeClass
    public static void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterClass
    public static void tearDownAndVerify() {
        System.setOut(originalOut);
        String allOutput = standardizeOutput(outputStream.toString());
        assertEquals(8632, allOutput.length(), 20000);
    }

    private static String standardizeOutput(String output) {
        return output
                .replaceAll("\n", "")
                .replaceAll("\\s", "")
                .replaceAll("\\p{C}", "")
                .replaceAll("[^a-zA-Z0-9]", "");
    }

    @Test
    public void testBitBoards() {
        BitBoards bitBoards = new BitBoards();
        System.out.println(bitBoards);
        System.out.println(BitBoards.isStuck(5));
        System.out.println(BitBoards.canDirection(5, 1));
        System.out.println(BitBoards.canDirection(3, 2));
        try {
            System.out.println(BitBoards.spawn(0b1110111000101110L));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        System.out.println(BitBoards.spawn(0b0000000000000000L));
        BitBoards.print(0b1110111000101110L);
        BitBoards.move(1, 8);
    }

    @Test
    public void testBoard() {
        Board board = new Board();
        System.out.println(board);
        System.out.println(board.move(1));
//        for (int i = 0; i < 36; i++) {
//            board.grid()[i] = 2;
//        }
        System.out.println(board.isStuck());
        System.out.println(board.isFull());
        System.out.println(board.canDirection(1));
        board.print();
        board.spawn();
        System.out.println(board.copy());
        System.out.println(board.hashCode());
        System.out.println(board.equals(board));
        System.out.println(board.equals(null));
        System.out.println(board.equals(1));
        System.out.println(board.equals(new Board()));
    }

    @Test
    public void testMeasure() {
        Board board = new Board();
        board.grid = new int[] {
                1,  2,  3,  4,  5,  6,
                1,  2,  3,  4,  5,  6,
                1,  2,  3,  4,  5,  6,
                1,  2,  3,  4,  5,  6,
                1,  2,  3,  4,  5,  6,
                1,  2,  3,  4,  5,  6
        };
        BestMeasure bestMeasure = new BestMeasure();
        System.out.println(bestMeasure.score(board));

        EnsambleMeasure ensambleMeasure = new EnsambleMeasure();
        ensambleMeasure.addMeasure(3, bestMeasure);
        System.out.println(ensambleMeasure.score(board));

        FreesMeasure freesMeasure = new FreesMeasure();
        System.out.println(freesMeasure.score(board));

        MonotonicityMeasure monotonicityMeasure = new MonotonicityMeasure();
        System.out.println(monotonicityMeasure.score(board));

        NegativeMeasure negativeMeasure = new NegativeMeasure(bestMeasure);
        System.out.println(negativeMeasure.score(board));

        SmoothMeasure smoothMeasure = new SmoothMeasure();
        System.out.println(smoothMeasure.score(board));

        SumMeasure sumMeasure = new SumMeasure();
        System.out.println(sumMeasure.score(board));

        ZeroMeasure zeroMeasure = new ZeroMeasure();
        System.out.println(zeroMeasure.score(board));
    }

    @Test
    public void testUCTStrategy() {
        UCTStrategy uctStrategy =
                new UCTStrategy(1, true, new BestMeasure(),
                        new SmoothStrategy("id"));
        UCTStrategy uctStrategy1 =
                new UCTStrategy(1, false, new BestMeasure(),
                        new SmoothStrategy("id"));
        Board board = new Board();
        for (int i = 0; i < 16; i++) {
            board.grid()[i] = 2;
        }
        try {
            uctStrategy.play(board).print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            uctStrategy1.play(board).print();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testChoiceNode() {
        Board board = new Board();
        for (int i = 0; i < 16; i++) {
            if (i % 2 == 0) {
                board.grid()[i] = 2;
            } else {
                board.grid()[i] = 4;
            }
        }
        ChoiceLeaf choiceLeaf = new ChoiceLeaf(board);
        List<Node> children = new ArrayList<>();
        children.add(choiceLeaf);

        ChoiceNode choiceNode = new ChoiceNode(board, 3, children);
        choiceNode.children = children;
        choiceNode.select(false);
        choiceNode.select(true);
        choiceNode.expand();

        ExitNode exitNode = new ExitNode(board, 2);
        exitNode.expand();
    }

    @Test
    public void testCyclicStrategy() {
        Board board = new Board();
        for (int i = 0; i < 16; i++) {
            board.grid()[i] = 2;
        }
        CyclicStrategy cyclicStrategy = new CyclicStrategy(1, 2, 3, 0);
        cyclicStrategy.play(board).print();
    }

    @Test
    public void testGreedyStrategy() {
        Board board = new Board();
        for (int i = 0; i < 16; i++) {
            board.grid()[i] = 2;
        }
        GreedyStrategy cyclicStrategy = new GreedyStrategy(new BestMeasure());
        cyclicStrategy.play(board).print();
    }
    @Test(timeout = 1000)
    public void testRandomStrategy() {
        Board board = new Board();
        for (int i = 0; i < 16; i++) {
            board.grid()[i] = 2;
        }
        RandomStrategy randomStrategy = new RandomStrategy();
        try {
            new RandomStrategy(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        board.grid()[35] = 0;
//        try {
//            randomStrategy.play(board).print();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    @Test
    public void testMove() {
        RandomStrategy pickStrategy = new RandomStrategy(0.5, 0.3, 0.2, 0.0);
        int move = pickStrategy.pickMove();
        System.out.println(move);
    }

    @Test
    public void testSmoothStrategy() {
        Board board = new Board();
        for (int i = 0; i < 16; i++) {
            board.grid()[i] = 2;
        }
        SmoothStrategy smoothStrategy = new SmoothStrategy("id");
        smoothStrategy.play(board).print();
    }
}
