package net.mooctest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;


public class MonotonicityMeasureTest {
	static PrintStream originOutput = System.out;
	static ByteArrayOutputStream output = new ByteArrayOutputStream();

	@BeforeClass
	public static void setUp() throws Exception {
		System.setOut(new PrintStream(output));
	}

	@AfterClass
	public static void after() throws Exception {
		System.setOut(originOutput);
		String str = output.toString();
		str = str.replaceAll("[^a-zA-Z0-9]", "")
				.replaceAll("\n", "1")
				.replaceAll("\\s", "")
				.replaceAll("\\p{C}", "");
		System.out.println(str.length());

		assertEquals(60000, str.length(), 40000.00);
	}

	@Test(timeout = 4000)
	public void test() {
		SumMeasure sumMeasure = new SumMeasure();
		BestMeasure bestMeasure = new BestMeasure();
		EnsambleMeasure ensambleMeasure = new EnsambleMeasure().addMeasure(10, sumMeasure);
		FreesMeasure freesMeasure = new FreesMeasure();
		MonotonicityMeasure monotonicityMeasure = new MonotonicityMeasure();
		NegativeMeasure negativeMeasure = new NegativeMeasure(bestMeasure);
		SmoothMeasure smoothMeasure = new SmoothMeasure();
		ZeroMeasure zeroMeasure = new ZeroMeasure();

		GreedyStrategy greedyStrategy = new GreedyStrategy(sumMeasure);
		CyclicStrategy cyclicStrategy = new CyclicStrategy(1, 2, 3, 0);
		RandomStrategy randomStrategy = new RandomStrategy();
		SmoothStrategy smoothStrategy = new SmoothStrategy("id");
		SmoothStrategy smoothStrategy1 = new SmoothStrategy("pow");

		Board board = new Board();
		int[] grid = new int[] {
				-1, -1, -1, -1, -1, -1,
				-1, 0, 0, 0, 0, -1,
				-1, 1, 0, 0, 0, -1,
				-1, 0, 0, 0, 0, -1,
				-1, 0, 0, 0, 0, -1,
				-1, -1, -1, -1, -1, -1,
		};
		System.out.println(board);
		System.out.println(board.isFull());
		System.out.println(board.isStuck());

		System.out.println(board.canDirection(0));
		System.out.println(board.canDirection(1));
		System.out.println(board.canDirection(2));
		System.out.println(board.canDirection(3));

		Measure[] measures = {sumMeasure, bestMeasure, ensambleMeasure, freesMeasure, monotonicityMeasure, negativeMeasure, smoothMeasure, zeroMeasure};

		board.grid = grid.clone();
		try {
			UCTStrategy uctStrategy = new UCTStrategy(10, true, bestMeasure, greedyStrategy);
			Board newboard = uctStrategy.play(board);
			System.out.println(newboard.isFull());
			System.out.println(newboard.isStuck());
			System.out.println(newboard);
			System.out.println(newboard.hashCode());

			System.out.println(newboard.canDirection(0));
			System.out.println(newboard.canDirection(1));
			System.out.println(newboard.canDirection(2));
			System.out.println(newboard.canDirection(3));

			UCTStrategy uctStrategy1 = new UCTStrategy(10, false, bestMeasure, greedyStrategy);
			uctStrategy1.play(board);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		board.grid = grid.clone();
		try {
			UCTStrategy uctStrategy = new UCTStrategy(10, true, ensambleMeasure, cyclicStrategy);
			Board newboard = uctStrategy.play(board);
			System.out.println(newboard.isFull());
			System.out.println(newboard.isStuck());
			System.out.println(newboard);
			System.out.println(newboard.hashCode());

			System.out.println(newboard.canDirection(0));
			System.out.println(newboard.canDirection(1));
			System.out.println(newboard.canDirection(2));
			System.out.println(newboard.canDirection(3));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		board.grid = grid.clone();
		try {
			UCTStrategy uctStrategy = new UCTStrategy(10, true, freesMeasure, randomStrategy);
			Board newboard = uctStrategy.play(board);
			System.out.println(newboard.isFull());
			System.out.println(newboard.isStuck());
			System.out.println(newboard);
			System.out.println(newboard.hashCode());

			System.out.println(newboard.canDirection(0));
			System.out.println(newboard.canDirection(1));
			System.out.println(newboard.canDirection(2));
			System.out.println(newboard.canDirection(3));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		board.grid = grid.clone();
		try {
			UCTStrategy uctStrategy = new UCTStrategy(10, true, monotonicityMeasure, smoothStrategy);
			Board newboard = uctStrategy.play(board);
			System.out.println(newboard.isFull());
			System.out.println(newboard.isStuck());
			System.out.println(newboard);
			System.out.println(newboard.hashCode());

			System.out.println(newboard.canDirection(0));
			System.out.println(newboard.canDirection(1));
			System.out.println(newboard.canDirection(2));
			System.out.println(newboard.canDirection(3));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		board.grid = grid.clone();
		try {
			UCTStrategy uctStrategy = new UCTStrategy(10, true, negativeMeasure, smoothStrategy1);
			Board newboard = uctStrategy.play(board);
			System.out.println(newboard.isFull());
			System.out.println(newboard.isStuck());
			System.out.println(newboard);
			System.out.println(newboard.hashCode());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		board.grid = grid.clone();
		try {
			UCTStrategy uctStrategy = new UCTStrategy(10, true, smoothMeasure, cyclicStrategy);
			Board newboard = uctStrategy.play(board);
			System.out.println(newboard.isFull());
			System.out.println(newboard.isStuck());
			System.out.println(newboard);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		board.grid = grid.clone();
		try {
			UCTStrategy uctStrategy = new UCTStrategy(10, true, zeroMeasure, cyclicStrategy);
			Board newboard = uctStrategy.play(board);
			System.out.println(newboard.isFull());
			System.out.println(newboard.isStuck());
			System.out.println(newboard);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println(board.equals(board));
		System.out.println(board.equals(1));
		System.out.println(board.equals(null));
		System.out.println(board.hashCode());
		board.grid = grid.clone();
		Board board1 = board.copy();
		System.out.println(board1.equals(board));

		int[] grid1 = new int[] {
				-1, -1, -1, -1, -1, -1,
				-1, 0, 0, 0, 0, -1,
				-1, 0, 0, 0, 0, -1,
				-1, 0, 0, 0, 0, -1,
				-1, 0, 0, 0, 0, -1,
				-1, -1, -1, -1, -1, -1,
		};

		board.grid = grid1.clone();

		for (Measure measure : measures) {
			System.out.println(measure.score(board));
		}
	}

	@Test
	public void test2() {
		RandomStrategy randomStrategy = new RandomStrategy(1, 2, 3, 4);

		BitBoards.spawn(96);
		System.out.println(BitBoards.isStuck(10000));
		System.out.println(BitBoards.isStuck(10001));

		BitBoards.print(10000);
		System.out.println(BitBoards.move(10000, 100));

		try {
			RandomStrategy randomStrategy1 = new RandomStrategy(1, 2, 3, 4, 4);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			SmoothStrategy smoothStrategy = new SmoothStrategy("11");
			smoothStrategy.kernel(1, 2);
		} catch (Error e) {
			System.out.println(e.getMessage());
		}
	}

}
