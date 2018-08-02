/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trees;

import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bdmyers
 */
public class TreeNodeTest {
		// Part 1a: write your tests here for removeA
    
    
                @Test
                public void testRemoveAbasic(){
                    int[] tree = {50,25,100};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeA(25);
                    int[] ex = {50,100}; 
                    assertEquals(t, TreeNode.bulkInsert(ex)); 
                }
                
                @Test    
                public void testRemoveAnodeNotFound(){
                    int[] tree =  {50,25,100};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeA(250);
                    assertEquals(t, TreeNode.bulkInsert(tree));

                            
                }
                @Test
                public void testRemoveARightWithChildren(){
                    int[] tree = {100,50,150,25,75,125,175,60,176,174,124,126,76,24,26};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeA(150); 
                    int[] ex = {100,50,25,24,26,75,60,76,126,125,124,175,174,176};

                    assertEquals(t,TreeNode.bulkInsert(ex) );
                }
                
                @Test
                public void testRemoveALeftWithChildren(){
                    int[] tree = {100,50,150,25,75,125,175,60,176,174,124,126,76,24,26};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeA(50); 
                    int[] ex = {100,26,25,24,75,60,76,150,125,124,126,175,174,176};
                    assertEquals(t,TreeNode.bulkInsert(ex) );
                }
                
                @Test
                public void testRemoveALeftWithChildren2(){
                    int[] tree = {100,50,150,75,125,175,60,176,174,124,126,76};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeA(50); 
                    int[] ex = {100,75,60,76,150,125,124,126,175,174,176};
                    assertEquals(t,TreeNode.bulkInsert(ex) );
                }
                
                @Test
                public void testRemoveARightWithChildren2(){
                    int[] tree = {100,50,150,25,75,125,60,124,126,76,24,26};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeA(150); 

                    int[] ex = {100,50,25,24,26,75,60,76,125,124,126};
                    assertEquals(t,TreeNode.bulkInsert(ex) );
                }
                
                
                                
                
                @Test
                public void testRemoveAroot(){
                    int[] tree = {100,50,25,75,125,175,60,79};
                    TreeNode t = TreeNode.bulkInsert(tree); 
                    t.removeA(100); 
                    int[] ex = {79,50,25,75,60,125,175}; 
                    assertEquals(t, TreeNode.bulkInsert(ex)); 
                

                    
                }

		// Part 1b: write your tests here for removeB (should be copies of your removeA tests)
                @Test
                public void testRemoveBbasic(){
                    int[] tree = {50,25,100};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeB(25);
                    int[] ex = {50,100}; 
                    assertEquals(t, TreeNode.bulkInsert(ex)); 
                }                
                @Test    
                public void testRemoveBnodeNotFound(){
                    int[] tree =  {50,25,100};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeB(250);
                    assertEquals(t, TreeNode.bulkInsert(tree));                          
                }
                @Test
                public void testRemoveBroot(){
                    int[] tree = {100,50,25,75,125,175,60,79};
                    TreeNode t = TreeNode.bulkInsert(tree);                     
                    t.removeB(100); 
                    int[] ex = {79,50,25,75,60,125,175}; 
                    assertEquals(t, TreeNode.bulkInsert(ex)); 
                                    
                }   
                @Test
                public void testRemoveBRightWithChildren(){
                    int[] tree = {100,50,150,25,75,125,175,60,176,174,124,126,76,24,26};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeB(150); 
                    int[] ex = {100,50,25,24,26,75,60,76,126,125,124,175,174,176};

                    assertEquals(t,TreeNode.bulkInsert(ex) );
                }                
                @Test
                public void testRemoveBLeftWithChildren(){
                    int[] tree = {100,50,150,25,75,125,175,60,176,174,124,126,76,24,26};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeB(50); 
                    int[] ex = {100,26,25,24,75,60,76,150,125,124,126,175,174,176};

                    assertEquals(t,TreeNode.bulkInsert(ex) );
                }  
                
                
                @Test
		public void testRemoveBRightWithChildren2(){
                    int[] tree = {100,50,150,25,75,125,60,124,126,76,24,26};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeA(150); 

                    int[] ex = {100,50,25,24,26,75,60,76,125,124,126};
                    assertEquals(t,TreeNode.bulkInsert(ex) );
                }
                
                @Test
                public void testRemoveBLeftWithChildren2(){
                    int[] tree = {100,50,150,75,125,175,60,176,174,124,126,76};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeB(50); 
                    int[] ex = {100,75,60,76,150,125,124,126,175,174,176};
                    assertEquals(t, TreeNode.bulkInsert(ex));
                }
                
                @Test
                public void testRemoveBLeftWithChildren3(){
                   int[] tree = {100,50,150,25,75,125,175,60,176,174,124,126,76,24,26};
                    TreeNode t = TreeNode.bulkInsert(tree);
                    t.removeB(75); 
                    int[] ex = {100,50,150,25,125,175,60,176,174,124,126,76,24,26};
                    assertEquals(t, TreeNode.bulkInsert(ex));
                }
                
		@Test
		public void testSize() {
				TreeNode t = new TreeNode(10);
				assertEquals(1, t.size());
				t.setLeft(new TreeNode(20));
				assertEquals(2, t.size());
		}

		@Test
		public void testInsert() {
			TreeNode n = new TreeNode(13);
			n.setRight(new TreeNode(20));
			n.getRight().setRight(new TreeNode(25));
			n.setLeft(new TreeNode(3));
			int[] ex = {13,20,3,25};
			assertEquals(n, TreeNode.bulkInsert(ex));
		}

		@Test
		public void testKeyRange() {
			int[] ex = {100,50,150,25,75,125,175,60,79};
			TreeNode root = TreeNode.bulkInsert(ex);
			Iterator<Integer> iter = root.keysInRange(54, 127);
			
			assertTrue(iter.hasNext());
			assertEquals((Integer)60, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)75, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)79, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)100, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)125, iter.next());
		}
		
		@Test
		public void testKeyRange2() {
			int[] ex = {100,50,150,25,75,125,175,60,79};
			TreeNode root = TreeNode.bulkInsert(ex);
			Iterator<Integer> iter = root.keysInRange(50, 100);
			
			assertTrue(iter.hasNext());
			assertEquals((Integer)50, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)60, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)75, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)79, iter.next());
		}
		
		@Test
		public void testKeyRange3() {
			int[] ex = {100,50,150,25,75,125,175,60,79};
			TreeNode root = TreeNode.bulkInsert(ex);
			Iterator<Integer> iter = root.keysInRange(100, 250);
			
			assertTrue(iter.hasNext());
			assertEquals((Integer)100, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)125, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)150, iter.next());
			assertTrue(iter.hasNext());
			assertEquals((Integer)175, iter.next());
		}
		
}

