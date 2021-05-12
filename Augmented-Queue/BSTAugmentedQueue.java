import java.util.NoSuchElementException;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

public class BSTAugmentedQueue implements IAugmentedQueue {
	private int[] queue = new int[1];
	private BSTNode root = null;
	private int front = 0;
	private int size = 0;

	@Override
	public int size() {
		return size;
	}

	@Override
	public void enqueue(int value) {
		if (size == queue.length) resize();

		queue[getIndex(front + size)] = value;

		// TODO: Add value to the BST also
		root = BSTNode.insert(root, value);
		size++;
	}

	@Override
	public int dequeue() {
		if (size == 0) throw new NoSuchElementException();
		int retVal = queue[front];
		front = getIndex(front+1);
		size--;
		root = BSTNode.delete(root, retVal);

		return retVal;
	}

	@Override
	public int front() {
		if (size == 0)
			throw new NoSuchElementException();
		return queue[front];
	}

	@Override
	public int back() {
		if (size == 0)
			throw new NoSuchElementException();

		return queue[getIndex(front + size - 1)];
	}

	@Override
	public int median() {
		if (size == 0)
			throw new NoSuchElementException();

		if (size % 2 == 0)
			return (root.get(size/2) + root.get((size-1)/2)) / 2;
		else
			return root.get(size/2);
	}

	@Override
	public int min() {
		if (size == 0)
			throw new NoSuchElementException();

		return root.get(0);
	}

	@Override
	public int max() {
		if (size == 0)
			throw new NoSuchElementException();

		return root.get(size-1);
	}

	private void resize() {
		int[] biggerQueue = new int[queue.length * 2];

		for (int i = 0; i < size; i++) {
			biggerQueue[i] = queue[getIndex(front + i)];
		}
		queue = biggerQueue;
		front = 0;
	}

	private int getIndex(int x) {
		x = x % queue.length;
		if (x < 0) x += queue.length;
		return x;
	}
}
