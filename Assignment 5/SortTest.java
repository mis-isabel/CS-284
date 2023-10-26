package Homework5;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class SortTest {

	Integer[] arr1 = {69, 777, 420};
	Integer[] arr2 = {789, 234, 3456};
	Integer[] arr3 = {908, 2645, 877};
	Integer[] arr4 = {1, 2, 3};
	Integer[] arr5 = {-8, 345, 0, 4, 642};
	Integer[] arr6 = {2349, 34, 957, 2989, 2345, 8470, -98};
	Integer[] arr7 = {-345, -643, -98345, 345, -485};
	Integer[] arr8 = {6542, -5463, 45};
	Double[] arr9 = {234.645, -651.2135, -34531.51};
	Double[] arr10 = {5435.551, -.54, 0.0};
	
    @Test
    void test() {
        Sort.sort(arr1);
        assertEquals(Arrays.toString(arr1), "[69, 420, 777]" );
        Sort.sort(arr2);
        assertEquals(Arrays.toString(arr2), "[234, 789, 3456]" );
        Sort.sort(arr3);
        assertEquals(Arrays.toString(arr3), "[877, 908, 2645]" );
        Sort.sort(arr4);
        assertEquals(Arrays.toString(arr4), "[1, 2, 3]" );
        Sort.sort(arr5);
        assertEquals(Arrays.toString(arr5), "[-8, 0, 4, 345, 642]" );
        Sort.sort(arr6);
        assertEquals(Arrays.toString(arr6), "[-98, 34, 957, 2345, 2349, 2989, 8470]" );
        Sort.sort(arr7);
        assertEquals(Arrays.toString(arr7), "[-98345, -643, -485, -345, 345]" );
        Sort.sort(arr8);
        assertEquals(Arrays.toString(arr8), "[-5463, 45, 6542]" );
        Sort.sort(arr9);
        assertEquals(Arrays.toString(arr9), "[-34531.51, -651.2135, 234.645]" );
        Sort.sort(arr10);
        assertEquals(Arrays.toString(arr10), "[-0.54, 0.0, 5435.551]" );
    }

}
