package com.timbar.uam.wmi.oop.boxoffice.database;

import com.timbar.uam.wmi.oop.boxoffice.domain.Movie;
import com.timbar.uam.wmi.oop.boxoffice.domain.MovieAdapter;
import com.timbar.uam.wmi.oop.boxoffice.domain.MovieInfoUnavailableException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMovieAdapter implements MovieAdapter {

    @Override
    public Movie getMovieById(int id) throws MovieInfoUnavailableException {
        try {
            try (Connection con = DataSourceManager.getDataSource().getConnection();
                 PreparedStatement ps = createPs4getMovieById(con, id)
            ) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return new Movie(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getInt("duration_mins")
                    );
                } else {
                    throw new MovieInfoUnavailableException();
                }
            }
        } catch (Exception e) {
            throw new MovieInfoUnavailableException(e);
        }
    }

    private PreparedStatement createPs4getMovieById(Connection con, int id) throws SQLException {
        String query = "select * from cinema_box_office.movie where id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        return ps;
    }
}
