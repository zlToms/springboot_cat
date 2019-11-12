package com.tang.zhen.film.dao.mapper;


import com.tang.zhen.film.comtroller.film.vo.response.filmdetail.ActorResultVO;
import com.tang.zhen.film.comtroller.film.vo.response.filmdetail.FilmDetailResultVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmMapperTest {
@Autowired
private FilmInfoTMapper infoTMapper;
@Autowired
private FilmActorTMapper actorTMapper;

@Test
public void describeFilmInfoById(){
    String filmId = "2";
    FilmDetailResultVO vo = infoTMapper.describeFilmDetailByFilmId(filmId);
    System.out.println(vo);

}

@Test
public void describeFilmInfoByNmae(){
    String filmName = "药神";
    FilmDetailResultVO vo = infoTMapper.describeFilmDetailByFilmName(filmName);
    System.out.println(vo);
}

@Test
public void describeActorsById(){
    String filmId = "2";
    List<ActorResultVO> vos = actorTMapper.describeActorsByFilmId(filmId);
    vos.stream().forEach(data ->
            System.out.println(data)
    );
}


}