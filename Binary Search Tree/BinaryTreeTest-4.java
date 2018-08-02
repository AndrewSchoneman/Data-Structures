
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class BinaryTreeTest {

		public BinaryTreeTest() {
		}

		@Test
		public void testInsertionAndtoArray() {
				BinaryTree bt = new BinaryTree();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				Object[] actual = bt.toArray();
				Object[] expected = {50, 2, 34, 19, 6, 22};
				assertArrayEquals(expected, actual);
		}

		@Test
		public void sumOfDepthLoopingTest() {
				BinaryTree<Integer> bt = new BinaryTree();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				int sum = bt.sumOfDepthLooping(2, new IntegerSum());
				assertEquals(47, sum);
		}
		@Test
		public void wantedNodesLoopingTest() {
				BinaryTree<Integer> bt = new BinaryTree();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				Integer[] expected = {50,2,6,34,22};
				List<Integer> answers = bt.wantedNodesLooping(new IsEven());
				assertArrayEquals(expected, answers.toArray());
		}

		// Example implementation of ReduceFunction used by the given test
		private static class IntegerSum implements ReduceFunction<Integer, Integer> {
				@Override
				public Integer combine(Integer soFar, Integer x) {
						return soFar+x;
				}

				@Override
				public Integer initialValue() {
						return 0;
				}
		}

		// Example implementation of IsEven used by the given test
		private static class IsEven implements Predicate<Integer> {
				@Override
				public boolean check(Integer data) {
						return data % 2 == 0;
				}
		}

		/* The staff will run your code on several additional JUnit tests of our own.
		   You must write additional tests below to provide more evidence that your
		   method implementations are correct. 
		
		   This test code is part of the assignment, just like the other code.

		   If you write a new test and it fails, GREAT! That means you are making
		   progress. Either the test is wrong and you just need to fix it, or it means you found
		   a bug in your BinaryTree code and now you can go fix it. Don't remove good tests just
		   because they fail.
		 */

        // write your new tests below here, using the @Test methods above as an example.
                @Test
                public void sumOfDepthRecursiveTest() {
				BinaryTree<Integer> bt = new BinaryTree();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				int sum = bt.sumOfDepthRecursive(2, new IntegerSum());
				assertEquals(47, sum);
		}
                
                public void sumOfDepthRecursiveTestTooFar() {
				BinaryTree<Integer> bt = new BinaryTree();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				int sum = bt.sumOfDepthRecursive(4, new IntegerSum());
				assertEquals(0, sum);
		}               
              
                @Test                
                public void sumOfDepthRecursiveTestLong() {       
                    		BinaryTree<Integer> bt = new BinaryTree();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
                                bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
                                bt.insertNode(13);
                                bt.insertNode(10);
                                bt.insertNode(7);
                                bt.insertNode(9);
                                int sum = bt.sumOfDepthRecursive(3, new IntegerSum()); 
                                assertEquals(120, sum);   
                }
                
                @Test                
                public void sumOfDepthLoopingTestLong() {       
                    		BinaryTree<Integer> bt = new BinaryTree();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
                                bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
                                bt.insertNode(13);
                                bt.insertNode(10);
                                bt.insertNode(7);
                                bt.insertNode(9);
                                int sum = bt.sumOfDepthLooping(3, new IntegerSum()); 
                                assertEquals(120, sum);
                }
                
                @Test
                public void sumOfDepthRecursiveTestAtRoot() { 
                                BinaryTree<Integer> bt = new BinaryTree();
                    		bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
                                int sum = bt.sumOfDepthRecursive(0, new IntegerSum()); 
                                assertEquals(50, sum);
                      
                  } 
                @Test
                public void sumOfDepthLoopingTooFar() {
				BinaryTree<Integer> bt = new BinaryTree();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				int sum = bt.sumOfDepthLooping(4, new IntegerSum());
				assertEquals(0, sum);
		}     
                
                @Test
                public void sumOfDepthLoopingTestAtRoot() { 
                                BinaryTree<Integer> bt = new BinaryTree();
                    		bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
                                int sum = bt.sumOfDepthLooping(0, new IntegerSum()); 
                                assertEquals(50, sum);
                      
                  }
                
                
                @Test
                public void wantedNodesLoopingTestAllOdd() {
				BinaryTree<Integer> bt = new BinaryTree();
				bt.insertNode(1);
				bt.insertNode(3);
				bt.insertNode(5);
				bt.insertNode(7);
				bt.insertNode(9);
				bt.insertNode(21);
				Integer[] expected = {};
				List<Integer> answers = bt.wantedNodesLooping(new IsEven());
				assertArrayEquals(expected, answers.toArray());
		}

		@Test
		public void wantedNodesLoopingTestLong() {
				BinaryTree<Integer> bt = new BinaryTree();
                    		bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
                                bt.insertNode(18);
                                bt.insertNode(7);
                                bt.insertNode(24);
                                bt.insertNode(9);
                                bt.insertNode(45);
                                bt.insertNode(47);
                                bt.insertNode(49);
                                bt.insertNode(51);
                                bt.insertNode(52);
                                Integer[] expected = {50,2,24,6,34,22, 18, 52};
                                List<Integer> answers = bt.wantedNodesLooping(new IsEven());
                                assertArrayEquals(expected, answers.toArray());
                                
                }
                
                @Test
		public void wantedNodesRecursiveTest() {
				BinaryTree<Integer> bt = new BinaryTree();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				Integer[] expected = {50,2,6,34,22};
				List<Integer> answers = bt.wantedNodesRecursive(new IsEven());
				assertArrayEquals(expected, answers.toArray());
                }
                
		@Test
		public void wantedNodesRecursiveTestLong() {
				BinaryTree<Integer> bt = new BinaryTree();
                    		bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
                                bt.insertNode(18);
                                bt.insertNode(7);
                                bt.insertNode(24);
                                bt.insertNode(9);
                                bt.insertNode(45);
                                bt.insertNode(47);
                                bt.insertNode(49);
                                bt.insertNode(51);
                                bt.insertNode(52);
                                Integer[] expected = {50,2,24,6,34,22, 18, 52};
                                List<Integer> answers = bt.wantedNodesRecursive(new IsEven());
                                assertArrayEquals(expected, answers.toArray());
                                
                }                
                public void wantedNodesRecusiveTestAllOdd() {
				BinaryTree<Integer> bt = new BinaryTree();
				bt.insertNode(1);
				bt.insertNode(3);
				bt.insertNode(5);
				bt.insertNode(7);
				bt.insertNode(9);
				bt.insertNode(21);
				Integer[] expected = {};
				List<Integer> answers = bt.wantedNodesRecursive(new IsEven());
				assertArrayEquals(expected, answers.toArray());
		}



}
