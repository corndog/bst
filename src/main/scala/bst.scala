package bst

trait Tree[+T] {

  def add[U >: T](y: U)(implicit Ord: Ordering[U]): Tree[U] =
    this match {
      case Empty => Tree(y, Empty, Empty)
      case Node(x, left, right) =>
        if (Ord.compare(y, x) == 0)
          this
        else if (Ord.compare(y, x) > 0)
          Tree(x, left, right.add(y))
        else
          Tree(x, left.add(y), right)
    }

  def find[U >: T](y: U)(implicit Ord: Ordering[U]): Boolean =
    this match {
      case Empty => false
      case Node(x, left, right) =>
        if (Ord.compare(y, x) == 0)
          true
        else if (Ord.compare(y,x) > 0)
          right.find(y)
        else
          left.find(y)
    }

  // not entirely clear what the thinking is regarding mapping over bsts, its a bit improper...

  // apply the function and return proper bst
  def map[U : Ordering](f: T => U) =
  	Tree(this.values.map(f):_*)

  // keep values in same place in tree, only return a tree if order is preserved
  def tryMap[U](f: T => U)(implicit Ord: Ordering[U]): Option[Tree[U]] = {
    val t = Tree.map(this)(f)
		if (Tree.isValid(t))
			Some(t)
		else
			None
  }

  def values: List[T] =
		this match {
			case Empty => List.empty[T]
			case Node(x, left, right) => x :: (left.values ::: right.values)
		}
  
}

case object Empty extends Tree[Nothing]

// https://gist.github.com/tpolecat/a5cb0dc9adeacc93f846835ed21c92d2
sealed abstract case class Node[T](x: T, left: Tree[T], right: Tree[T]) extends Tree[T]

object Tree {
  private def apply[T](x: T, left: Tree[T], right: Tree[T]) = new Node(x, left, right) {}

  def apply[T](values: T*)(implicit Ord: Ordering[T]): Tree[T] =
		values.toList match {
			case Nil => Empty
			case h :: t => {
				val (less, greater) = t.partition(x => Ord.compare(x, h) < 0)
				Tree(h, Tree(less:_*), Tree(greater:_*))
			}
		}

	def init[T : Ordering](x: T): Tree[T] = apply(x, Empty, Empty)

  def tryInit[T: Ordering](x: T, left: Tree[T], right: Tree[T]): Option[Tree[T]] = {
    val t = apply(x, left, right)
	  if (isValid(t))
	    Some(t)
	  else
	    None
  }

	private def isValid[T](tree: Tree[T])(implicit Ord: Ordering[T]): Boolean =
  	tree match {
  		case Empty => true
  		case Node(x, Empty, Empty) => true
  		case Node(x, left: Node[T], Empty) =>
  			Ord.compare(left.x, x) <= 0 && isValid(left)
  		case Node(x, Empty, right: Node[T]) => 
  			Ord.compare(right.x, x) >= 0 && isValid(right)
  		case Node(x, left: Node[T], right: Node[T]) =>
  			Ord.compare(left.x, x) <= 0 && Ord.compare(right.x, x) >= 0 && isValid(left) && isValid(right)
  	}

  private def map[T, U](tree: Tree[T])(f: T => U): Tree[U] =
  	tree match {
   		case Empty => Empty
   		case Node(x, left, right) => Tree(f(x), map(left)(f), map(right)(f))
  	}

}

