package com.miloszjakubanis.flusterstorm.user

class UserFactory extends (String => User):
  private[this] var id: Int = 0

  override def apply(name: String): User =
    id += 1
    AbstractUser(id, name)
