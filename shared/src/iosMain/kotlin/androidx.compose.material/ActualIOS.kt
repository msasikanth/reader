package androidx.compose.material

import kotlin.native.concurrent.AtomicReference

actual class InternalAtomicReference<V> actual constructor(value: V) {

  private val atomicReference = AtomicReference(value)

  actual fun get(): V {
    return atomicReference.value
  }

  actual fun set(value: V) {
    atomicReference.value = value
  }

  actual fun compareAndSet(expect: V, newValue: V): Boolean {
    return atomicReference.compareAndSet(expect, newValue)
  }
}
