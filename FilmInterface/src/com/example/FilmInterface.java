package com.example;

import java.util.List;


public interface FilmInterface {
  void deleteNote (int id);
  void updateNote (int id, String text);
  int newNote (String text, int projectId);
  List<Object[]> getNotes ();
  List<Note> getNotesWithCriteriaApi ();
}