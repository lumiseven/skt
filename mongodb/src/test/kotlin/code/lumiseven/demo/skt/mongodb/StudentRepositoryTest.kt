package code.lumiseven.demo.skt.mongodb

import code.lumiseven.demo.skt.mongodb.entity.Student
import code.lumiseven.demo.skt.mongodb.repository.StudentRepository
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Filters.gte
import org.bson.Document
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.Aggregation.limit
import org.springframework.data.mongodb.core.aggregation.Aggregation.sort
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@ExtendWith(SpringExtension::class)
class StudentRepositoryTest {

    @Autowired
    private lateinit var studentRepository: StudentRepository

    @Autowired
    private lateinit var mongoTemplate: MongoTemplate

    @Test
    @Order(1)
    fun saveStudents() {
        val student = Student()
        student.personId = 20180101L
        student.age = 22
        student.name = "张三"

        studentRepository.deleteAll()

        for(i in 0..15) {
            student.age = 22 - i
            student.personId = 20180101L + i
            studentRepository.save(student)
            student.date = Date()
        }
    }

    @Test
    @Order(2)
    fun testFindByNameAndAge() {
        val pageable = PageRequest.of(0, 5)

        val studentPage = studentRepository.findByNameRegexAndAgeGreaterThanEqual("张三", 20, pageable)

        assertEquals(3, studentPage.content.size)
    }

    @Test
    @Order(3)
    fun testFindByNameAndAge1() {
        val pageable = PageRequest.of(0, 5)
        val studentPage = studentRepository.findByAgeIndividual("张三", 10, pageable)
        assertEquals(5, studentPage.content.size)
    }

    @Test
    @Order(4)
    fun testDeleteByAgeIn() {
        studentRepository.deleteByAgeIn(20, 22)
        val students = studentRepository.findAll()
        assertEquals(13, students.size)
    }

    @Test
    @Order(5)
    fun testSortByAge() {
        val aggregation = Aggregation.newAggregation(Student::class.java, sort(Sort.Direction.DESC, "age"), limit(1))
        val student = mongoTemplate.aggregate(aggregation, Student::class.java).mappedResults

        assertEquals(19, student[0].age)
    }

    @Test
    @Order(6)
    fun testFindCount() {
        val n = mongoTemplate.db.getCollection("student").countDocuments()
        assertEquals(13, n)
    }

    @Test
    @Order(7)
    fun testFindByName() {
        val n = mongoTemplate.db.getCollection("student").find()
            .filter(eq("name", "张三"))
            .filter(gte("age", 19)).count()
        assertEquals(1, n)
    }

    @Test
    @Order(8)
    fun testFindByNameLimit() {
        val n = mongoTemplate.db.getCollection("student").find()
            .filter(eq("name", "张三"))
            .limit(5)
            .count()
        assertEquals(5, n)
    }

    @Test
    @Order(9)
    fun testFindByNameCurse() {
        val studentCursor = mongoTemplate.db.getCollection("student").find()
            .filter(eq("name", "张三"))
            .cursor()
        while(studentCursor.hasNext()){
            assertEquals("张三", studentCursor.next()["name"])
        }
        studentCursor.close()
    }

    @Test
    @Order(10)
    fun testFindObjectId() {
        val studentCursor = mongoTemplate.db.getCollection("student").find()
            .filter(eq("name", "张三"))
            .cursor()
        while(studentCursor.hasNext()){
            val document = studentCursor.next()
            println("_id: ${document["_id"]}, date: ${document["date"]}")
        }
        studentCursor.close()
    }

    @Test
    @Order(11)
    fun testDelete() {
        val document = Document()
        document["name"] = "张三"

        mongoTemplate.db.getCollection("student").deleteMany(document)
        val n= mongoTemplate.db.getCollection("student").countDocuments()

        assertEquals(0, n)
    }

}