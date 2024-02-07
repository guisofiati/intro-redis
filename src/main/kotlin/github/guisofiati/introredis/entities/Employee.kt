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
    var id: UUID? = null
    var name: String? = null
    var age: Int? = null
    var email: String? = null
    var phone: String? = null
}