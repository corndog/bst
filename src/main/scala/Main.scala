import bst._

// trait Tree[+T] {

//   def add[U >: T](y: U)(implicit Ord: Ordering[U]): Tree[U] =
//     this match {
//       case Empty => Node(y, Empty, Empty)
//       case n @ Node(x, left, right) =>
//         if (Ord.compare(y, x) == 0)
//           this
//         else if (Ord.compare(y, x) > 0)
//           n.copy(right = right.add(y))
//         else
//           n.copy(left = left.add(y))
//     }

//   def find[U >: T](y: U)(implicit Ord: Ordering[U]): Boolean =
//     this match {
//       case Empty => false
//       case Node(x, left, right) =>
//         if (Ord.compare(y, x) == 0)
//           true
//         else if (Ord.compare(y,x) > 0)
//           right.find(y)
//         else
//           left.find(y)
//     }

//   def map[U](f: T => U) : Tree[U] =
//     this match {
//       case Empty => Empty
//       case Node(x, left, right) => Node(f(x), left.map(f), right.map(f))
//     }
// }

// case  object Empty extends Tree[Nothing]

// case class Node[T](x: T, left: Tree[T], right: Tree[T]) extends Tree[T]

case class Stuff(x: Int, y: String)

object Main extends App {

  //val bst = Node(5, Node(2, Empty, Empty), Node(25, Empty, Node(100, Empty, Empty)))  // NOPE  :)
  // val t = Tree(5, Empty, Empty) // NOPE  :)
  val bst = Tree.init(5)
  println(s"bst?  ${bst}")

  //val sbts = Tree.init(Stuff(4, "cat"))
  val a = bst.find(5)
  val b = bst.find(9)
  val c = bst.add(21)
  val cc = c.add(19)
  println(s"tree $cc, vals ${cc.values}")

  println(s"$a\n$b\n$c")

  val d = Tree.init(22)
  val e = Tree.tryInit(8, d, Empty)
  println(s"$d\n$e")

  val g = Tree.tryInit(10, bst, d)
  println(s"$g")
  // val c = bst.find(100)
  // val d = bst.find(2)

  // val e = Empty
  // val f = Empty.add(99)
  // println(s"$e AND $f")
  // val g = f.add(44)
  // println(s"$e AND $f AND $g")
  // val h = g.add(200)
  // println(s"$e AND $f AND $g AND $h")
  // val hh = h.find(44)
  // println(s"$hh")

  // val bb = Empty.add(4).add(99).add(23).add(63).add(12)
  // val bb3 = bb.map(_ * 3)

  // println(s"$bb")
  // println(s"$bb3")

  // val someNums = List(66,3, 8, 21)
  // val sn =
  //   someNums.foldLeft(Empty: Tree[Int]){ (acc, x) => acc.add(x) }

  // println(s"$sn")

}