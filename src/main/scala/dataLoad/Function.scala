package dataLoad

/**
  * Created by Chang Yu on 2016/10/3.
  */
import scala.util.Try

/**
  * Created by scalaprof on 10/2/16.
  */
object Function {

  /**
    * The map2 function. You already know this one!
    *
    * @param t1y parameter 1 wrapped in Try
    * @param t2y parameter 2 wrapped in Try
    * @param f   function that takes two parameters of types T1 and T2 and returns a value of R
    * @tparam T1 the type of parameter 1
    * @tparam T2 the type of parameter 2
    * @tparam R  the type of the result of function f
    * @return a value of R, wrapped in Try
    */
  def map2[T1, T2, R](t1y: Try[T1], t2y: Try[T2])(f: (T1, T2) => R): Try[R] =                        // Try(f(t1y.get, t2y.get))第一，对于Option() 不适用，
  // some times works for
     for(x<-t1y ; y<-t2y) yield f(x,y)
  // use get to get parameters in scala ?
  /**
    * The map3 function. Much like map2
    *
    * @param t1y parameter 1 wrapped in Try
    * @param t2y parameter 2 wrapped in Try
    * @param t3y parameter 3 wrapped in Try
    * @param f   function that takes three parameters of types T1, T2 and T3 and returns a value of R
    * @tparam T1 the type of parameter 1
    * @tparam T2 the type of parameter 2
    * @tparam T3 the type of parameter 3
    * @tparam R  the type of the result of function f
    * @return a value of R, wrapped in Try
    */
  def map3[T1, T2, T3, R](t1y: Try[T1], t2y: Try[T2], t3y: Try[T3])(f: (T1, T2, T3) => R): Try[R] = for(x<-t1y ; y<-t2y; z<-t3y) yield f(x, y, z) // TODO 5

  /**t
    * You get the idea...
    */
  def map7[T1, T2, T3, T4, T5, T6, T7, R](t1y: Try[T1], t2y: Try[T2], t3y: Try[T3], t4y: Try[T4], t5y: Try[T5], t6y: Try[T6], t7y: Try[T7])(f: (T1, T2, T3, T4, T5, T6, T7) => R): Try[R] = for(x1<-t1y; x2<-t2y; x3<-t3y; x4<-t4y; x5<-t5y;
   x6<-t6y; x7<-t7y) yield f(x1, x2, x3, x4, x5 , x6, x7) // TODO 5

  /**
    * Lift function to transform a function f of type T=>R into a function of type Try[T]=>Try[R]
    *
    * @param f the function we start with, of type T=>R
    * @tparam T the type of the parameter to f
    * @tparam R the type of the result of f
    * @return a function of type Try[T]=>Try[R]
    */
  // TODO 4 (You know this one)
  def lift[T, R](f: T => R): Try[T] => Try[R] = _ map f //???
 //for any type of parameters use f function to get the map
  /**
    * Lift function to transform a function f of type (T1,T2)=>R into a function of type (Try[T1],Try[T2])=>Try[R]
    *
    * @param f the function we start with, of type (T1,T2)=>R
    * @tparam T1 the type of the first parameter to f
    * @tparam T2 the type of the second parameter to f
    * @tparam R  the type of the result of f
    * @return a function of type (Try[T1],Try[T2])=>Try[R]
    */
  // TODO 8 (Think Simple, Elegant, Obvious)
  def lift2[T1, T2, R](f: (T1, T2) => R): (Try[T1], Try[T2]) => Try[R] =  map2(_,_)(f) //(x:Try[T1] , y:Try[T2])=> map2(x,y)(f) // map2(_,_)(f)//

  //map2
  /*
  {
    def g(a:Try[T1],b:Try[T2]):Try[R]= for( t1<-a; t2<-b) yield f(t1,t2)
    g
  }

*/
  /**
    * Lift function to transform a function f of type (T1,T2,T3)=>R into a function of type (Try[T1],Try[T2],Try[T3])=>Try[R]
    *
    * @param f the function we start with, of type (T1,T2,T3)=>R
    * @tparam T1 the type of the first parameter to f
    * @tparam T2 the type of the second parameter to f
    * @tparam T3 the type of the third parameter to f
    * @tparam R  the type of the result of f
    * @return a function of type (Try[T1],Try[T2],Try[T3])=>Try[R]
    */
  // TODO 8 (If you can do lift2, you can do lift3)(
  def lift3[T1, T2, T3, R](f: (T1, T2, T3) => R): (Try[T1], Try[T2], Try[T3]) => Try[R] =map3(_,_,_)(f) //(x:Try[T1],y:Try[T2] ,z :Try[T3]) => map3(x,y,z)(f)
   //使用 scala 益民函数解决问题
 // 使用map, 第一使用 匿名函数传入map 需要传入的值， 的人，写入需要转化的函数。
    // what returns for the g2
 //???

  //  def liftMap[T1, T2, R](f: T1 => T2 => R): T1=>Try[T2]=>Try[R] = { s => lift(f(s))}

  /**
    * This method inverts the order of the first two parameters of a two-(or more-)parameter curried function.
    *
    * @param f the function
    * @tparam T1 the type of the first parameter
    * @tparam T2 the type of the second parameter
    * @tparam R  the result type
    * @return a curried function which takes the second parameter first
    */
  // TODO 7 Hint: think about writing an anonymous function that takes a t2, then a t1 and returns the appropriate result
  // NOTE: you won't be able to use the "_" character here because the compiler infers an ordering that you don't want
  def invert2[T1, T2, R](f: T1 => T2 => R): T2 => T1 => R = (x:T2)=>(y:T1)=>f(y)(x)
  /*
  {
    def g(b:T2)(a:T1):R = f(a)(b)
    g
  }//???
*/
  /**
    * This method inverts the order of the first three parameters of a three-(or more-)parameter curried function.
    *
    * @param f the function
    * @tparam T1 the type of the first parameter
    * @tparam T2 the type of the second parameter
    * @tparam T3 the type of the third parameter
    * @tparam R  the result type
    * @return a curried function which takes the third parameter first, then the second, etc.
    */
  // TODO 4 If you can do invert2, you can do this one too
  def invert3[T1, T2, T3, R](f: T1 => T2 => T3 => R): T3 => T2 => T1 => R =(a:T3)=>(b:T2)=>(c:T1)=> f(c)(b)(a)
/*
  {
   def g(c:T3)(b:T2)(a:T1) : R =f(a)(b)(c)
    g
}  // the meaning of sequence , why use this kind to solve curring
  */

  /**
    * This method inverts the order of the first four parameters of a four-(or more-)parameter curried function.
    *
    * @param f the function
    * @tparam T1 the type of the first parameter
    * @tparam T2 the type of the second parameter
    * @tparam T3 the type of the third parameter
    * @tparam T4 the type of the fourth parameter
    * @tparam R  the result type
    * @return a curried function which takes the fourth parameter first, then the third, etc.
    */
  // TODO 4 If you can do invert3, you can do this one too
  def invert4[T1, T2, T3, T4, R](f: T1 => T2 => T3 => T4 => R): T4 => T3 => T2 => T1 => R = (x:T4)=>(y:T3)=>(z:T2)=>(k:T1)=>f(k)(z)(y)(x)
/*
  {
  def g(d: T4)(c: T3)(b:T2)(a : T1) : R = f(a)(b)(c)(d)// how the value transefred here
  g// why g here
}
 */
  /**
    * This method uncurries the first two parameters of a three- (or more-)
    * parameter curried function.
    * The result is a (curried) function whose first parameter is a tuple of the first two parameters of f;
    * whose second parameter is the third parameter, etc.
    *
    * @param f the function
    * @tparam T1 the type of the first parameter
    * @tparam T2 the type of the second parameter
    * @tparam T3 the type of the third parameter
    * @tparam R  the result type of function f
    * @return a (curried) function of type (T1,T2)=>T4=>R
    */
  // TODO 11 This one is a bit harder. But again, think in terms of an anonymous function that is what you want to return
  def uncurried2[T1, T2, T3, R](f: T1 => T2 => T3 => R): (T1, T2) => T3 => R = {(t1,t2)=>{t3=>f(t1)(t2)(t3)}} //(x:T1,y:T2)=>(z:T3) =>f(x)(y)(z)
 //  what is the disadvantage of my method?
/*
  {   // front funct compare to the input sequence
  def h(a :T1, b:T2)(c:T3) : R = f(a)(b)(c)
  h

}

*/
  /**
    * This method uncurries the first three parameters of a four- (or more-)
    * parameter curried function.
    * The result is a (curried) function whose first parameter is a tuple of the first two parameters of f;
    * whose second parameter is the third parameter, etc.
    *
    * @param f the function
    * @tparam T1 the type of the first parameter
    * @tparam T2 the type of the second parameter
    * @tparam T3 the type of the third parameter
    * @tparam T4 the type of the fourth parameter
    * @tparam R  the result type of function f
    * @return a (curried) function of type (T1,T2,T3)=>T4=>R
    */
  // TODO 8 If you can do uncurried2, then you can do this one
  def uncurried3[T1, T2, T3, T4, R](f: T1 => T2 => T3 => T4 => R): (T1, T2, T3) => T4 => R =  {(t1, t2, t3)=>{t4=>f(t1)(t2)(t3)(t4)}}// (a: T1, b : T2, c: T3)=>(d: T4)=>f(a)(b)(c)(d)
  /*
  {
     def ups(a: T1, b : T2, c: T3)(d: T4): R= f(a)(b)(c)(d)
     ups
  }
*/
}