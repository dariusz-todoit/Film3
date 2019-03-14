package com.example;



import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
// import javax.persistence.metamodel.Metamodel;
// import javax.persistence.metamodel.EntityType;
import javax.persistence.TypedQuery;

import javax.persistence.EntityManager;
// import javax.persistence.LockModeType;



@Stateless @Remote
public class FilmEJB implements FilmInterface {
  
 
  @PersistenceContext(unitName="something")
  private EntityManager entityManager;
  
    
  @Override
  public List<Object[]> getNotes () {
    Query query = entityManager.createQuery 
        ("SELECT n.noteId, n.note, n.projectID FROM Note n ORDER BY n.noteId");
   
    @SuppressWarnings("unchecked")
    List<Object[]> rs = (List<Object[]>) query.getResultList();     
    
    return rs;    
  }
  
 
  @Override
  public void deleteNote (int id) {
    Note note = entityManager.find(Note.class, id);
    entityManager.remove(entityManager.merge(note));
  }
  
  @Override
  public void updateNote (int id, String text) {
    Note note = entityManager.find (Note.class, id);
    note.setNote(text);
    entityManager.persist(note);
  }
  
  @Override
  public int newNote (String text, int projectId) {
    Note note = new Note();
    note.setNote(text);
    note.setProjectID(projectId);
    entityManager.persist(note);
    entityManager.flush();    
    
    return note.getNoteID();
  }


  

	
	@Override
	public List<Note> getNotesWithCriteriaApi() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		// Metamodel m = entityManager.getMetamodel();
		
		// EntityType<Note> Note_ = m.entity(Note.class);
		
		CriteriaQuery<Note> cq = cb.createQuery(Note.class);
		Root<Note> note = cq.from(Note.class);
		cq.where(cb.lt(note.get(Note_.noteId), 90));
		
		
		// cq.where(note.get(Note_.noteId).isNull());
		
		cq.select(note);
		TypedQuery<Note> q = entityManager.createQuery(cq);
		List<Note> notes = q.getResultList();
		
		
		return notes;


	}  

	
}
