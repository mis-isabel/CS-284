package Homework4;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TreapJUnit {
	Treap<String> treap1 = new Treap<String>();
	Treap<Integer> testTree = new Treap<Integer>();
	Treap<String> testTree2 = new Treap<String>();
	
	@Test
	public void testAdd() {
			assertEquals(treap1.add("s", 8), true);
			assertEquals(treap1.toString(),"(key=s, priority=8)\n" + 
					" null\n" + 
					" null\n");
			//add duplicate 
			assertEquals(treap1.add("s", 9), false);
			
			assertEquals(treap1.add("q", 9), true);
			//add duplicate priority
			assertEquals(treap1.add("z", 9), false);
			
			assertEquals(treap1.toString(),"(key=q, priority=9)\n" + 
					" null\n" + 
					" (key=s, priority=8)\n" + 
					"  null\n" + 
					"  null\n");
			
			assertEquals(treap1.add("q", 9), false);
			assertEquals(treap1.add("b", 2), true);
			assertEquals(treap1.toString(), "(key=q, priority=9)\n" + 
					" (key=b, priority=2)\n" + 
					"  null\n" + 
					"  null\n" + 
					" (key=s, priority=8)\n" + 
					"  null\n" + 
					"  null\n");
	}
	
	@Test
	public void testDelete() {
		assertEquals(testTree2.add("p", 99), true);
		assertEquals(testTree2.add("e", 82), true);
		assertEquals(testTree2.add("u", 75), true);
		assertEquals(testTree2.add("b", 60), true);
		assertEquals(testTree2.add("j", 69), true);
		assertEquals(testTree2.add("r", 40), true);
		assertEquals(testTree2.add("z", 47), true);
		assertEquals(testTree2.add("w", 32), true);
		
		//remove element not in the list
		assertEquals(testTree2.delete("y"), false);
		
		assertEquals(testTree2.delete("z"), true);
		assertEquals(testTree2.toString(), "(key=p, priority=99)\n" + 
				" (key=e, priority=82)\n" + 
				"  (key=b, priority=60)\n" + 
				"   null\n" + 
				"   null\n" + 
				"  (key=j, priority=69)\n" + 
				"   null\n" + 
				"   null\n" + 
				" (key=u, priority=75)\n" + 
				"  (key=r, priority=40)\n" + 
				"   null\n" + 
				"   null\n" + 
				"  (key=w, priority=32)\n" + 
				"");
	}
	
	@Test
	public void testFind() {
		assertEquals(testTree2.add("p", 99), true);
		assertEquals(testTree2.add("e", 82), true);
		assertEquals(testTree2.add("u", 75), true);
		assertEquals(testTree2.add("b", 60), true);
		assertEquals(testTree2.add("j", 69), true);
		assertEquals(testTree2.add("r", 40), true);
		assertEquals(testTree2.add("z", 47), true);
		assertEquals(testTree2.add("w", 32), true);
		
		assertEquals(testTree2.find("p"), true);
		assertEquals(testTree2.find("e"), true);
		assertEquals(testTree2.find("u"), true);
		assertEquals(testTree2.find("b"), true);
		assertEquals(testTree2.find("j"), true);
		assertEquals(testTree2.find("r"), true);
		assertEquals(testTree2.find("z"), true);
		assertEquals(testTree2.find("w"), true);
		
		assertEquals(testTree2.find("y"), false);
	}

	@Test
	public void testToString() {
		testTree.add(4,19);
		testTree.add(2,31);
		testTree.add(6,70);
		testTree.add(1,84);	
		testTree.add(3,12);
		testTree.add(5,83);
		testTree.add(7,26);
		
		assertEquals(testTree.toString(), "(key=1, priority=84)\n" + 
				" null\n" + 
				" (key=5, priority=83)\n" + 
				"  (key=2, priority=31)\n" + 
				"   null\n" + 
				"   (key=4, priority=19)\n" + 
				"    (key=3, priority=12)\n" + 
				"     null\n" + 
				"     null\n" + 
				"    null\n" + 
				"  (key=6, priority=70)\n" + 
				"   null\n" + 
				"   (key=7, priority=26)\n" + 
				"    null\n" + 
				"    null\n");
	}
}
