package github.guisofiati.introredis.entities

import jakarta.persistence.*
import lombok.Data
import java.io.Serializable
import java.util.UUID

@Data
@Entity
@Table(name = "TB_EMPLOYEES")
class Employee: Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    lateinit var id: UUID
    lateinit var name: String
    var age: Int = 0
    lateinit var email: String
    lateinit var phone: String
}