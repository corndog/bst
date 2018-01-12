import bst._


object Main extends App {

  val bst = Tree(5)
  println(s"bst?  ${bst}")

  val a = bst.find(5)
  val b = bst.find(9)
  val c = bst.add(21)
  val cc = c.add(19)
  println(s"tree $cc, vals ${cc.values}")

  println(s"$a\n$b\n$c")

  val d = Tree(22)
  val e = Tree.tryInit(8, d, Empty)
  println(s"$d\n$e")

  val g = Tree.tryInit(10, bst, d)
  println(s"$g")
 
  val t = Tree(45,80,32, 21, 59, 99, 12, 8, 17, 101, 4, 61)
  println(s"$t")
  println(s"${t.inOrder}")
  println(s"${t.preOrder}")
  println(s"${t.postOrder}")

}