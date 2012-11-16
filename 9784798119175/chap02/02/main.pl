#!/usr/bin/env perl
use strict;
use warnings;
use HTTP::RequestProcessor;
use HTTP::RequestHandler::GET;
use HTTP::RequestHandler::POST;

my $p = HTTP::RequestProcessor->new;

$p->add_handler(HTTP::RequestHandler::GET->new);
$p->add_handler(HTTP::RequestHandler::POST->new);

$p->handle_request(HTTP::Request->new(GET => 'http://www.endeworks.jp/'));
$p->handle_request(HTTP::Request->new(POST => 'http://www.endeworks.jp/'));
